package sr.grpc.hw;

import com.google.common.collect.ImmutableSet;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.StreamObserver;
import sr.grpc.gen.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GrpcClient {
    private final Map<String, ServerDesc> servers = new HashMap<>();
    private final Map<Integer, ManagedChannel> streamingChannel = new HashMap<>();
    private final AtomicInteger nextStreamId = new AtomicInteger(0);

    public List<Sensor> getAllSensorsOnServer(String server) {
        return getSensorsStub(server)
                .describeSensors(Empty.getDefaultInstance())
                .getSensorsList()
                .stream()
                .map(desc -> Sensor.fromDescription(desc, server))
                .collect(Collectors.toList());

    }

    public Sensor getSensorDescription(String server, int id) {
        var description = getSensorsStub(server)
                .describeSensor(GenericSensorControlerDescArg.newBuilder().setSensorId(id).build());
        return Sensor.fromDescription(description, server);

    }

    public double getSensorValue(Sensor sensor) {
        return getSensorsStub(sensor.server())
                .getReadings(GenericSensorControlerGetArg.newBuilder().setSensorId(sensor.id()).build())
                .getCurrentValue();
    }

    public int subSensorValue(Sensor sensor, int refreshMilis, Consumer<Double> callback) {
        int channelId = nextStreamId.getAndIncrement();
        var channel = ManagedChannelBuilder.forTarget(sensor.server()).usePlaintext().build();
        streamingChannel.put(channelId, channel);

        var stub = GenericSensorControlerGrpc.newStub(channel);
        StreamObserver<GenericSensorControlerSensorValue> observer = new StreamObserver<>() {
            @Override
            public void onNext(GenericSensorControlerSensorValue value) {
                callback.accept(value.getCurrentValue());

            }

            @Override
            public void onError(Throwable t) {
                if (t instanceof StatusException ex) {
                    if (Objects.requireNonNull(ex.getStatus().getCode()) == Status.Code.CANCELLED) {
                        System.out.printf("the streaming call was cancelled for channelId=%d", channelId);
                    } else {
                        System.out.printf("unknown error occured %s\n", ex.getMessage());
                    }
                }
            }

            @Override
            public void onCompleted() {
            }
        };
        stub.subReadings(GenericSensorControlerSubArg.newBuilder()
                        .setSensorId(sensor.id())
                        .setDelayMilis(refreshMilis)
                        .build(),
                observer
        );
        return channelId;
    }

    public boolean cancelSub(int channelId) {
        if (!streamingChannel.containsKey(channelId)) {
            System.out.println("unknown channel id");
            return false;
        }
        streamingChannel.remove(channelId).shutdownNow();
        return true;
    }

    public SmartBulbState getSmartbulbState(String server) {
        return getSmartbulbStub(server)
                .getState(Empty.getDefaultInstance())
                .getState();
    }

    public void setSmartbulbState(String server, SmartBulbState state) {
        var stub = getSmartbulbStub(server);
        var ignored = switch (state) {
            case ON -> stub.turnOn(Empty.getDefaultInstance());
            case OFF -> stub.turnOff(Empty.getDefaultInstance());
            case UNRECOGNIZED -> null;
        };
    }

    public Set<String> knownServers() {
        return ImmutableSet.copyOf(servers.keySet());
    }

    private GenericSensorControlerGrpc.GenericSensorControlerBlockingStub getSensorsStub(String server) {
        return (GenericSensorControlerGrpc.GenericSensorControlerBlockingStub) servers
                .computeIfAbsent(server, ServerDesc::fromTarget)
                .getServiceObject(ServiceType.SENSORS);
    }

    private SmartBulbGrpc.SmartBulbBlockingStub getSmartbulbStub(String server) {
        return (SmartBulbGrpc.SmartBulbBlockingStub) servers
                .computeIfAbsent(server, ServerDesc::fromTarget)
                .getServiceObject(ServiceType.SMARTBULB);
    }

    private enum ServiceType {
        SENSORS(GenericSensorControlerGrpc::newBlockingStub,
                GenericSensorControlerGrpc.GenericSensorControlerBlockingStub.class),
        SMARTBULB(SmartBulbGrpc::newBlockingStub,
                SmartBulbGrpc.SmartBulbBlockingStub.class);

        public final Class<? extends AbstractBlockingStub<?>> type;
        private final Function<ManagedChannel, Object> constructor;

        ServiceType(Function<ManagedChannel, Object> constructor, Class<? extends AbstractBlockingStub<?>> type) {

            this.constructor = constructor;
            this.type = type;
        }

        public Object construct(ManagedChannel channel) {
            return constructor.apply(channel);
        }
    }

    private record ServerDesc(ManagedChannel channel, EnumMap<ServiceType, Object> stubs) {
        public static ServerDesc fromTarget(String target) {
            return new ServerDesc(ManagedChannelBuilder.forTarget(target)
                    .usePlaintext()
                    .build(), new EnumMap<>(ServiceType.class));
        }

        public Object getServiceObject(ServiceType type) {
            return stubs.computeIfAbsent(type, key -> type.construct(channel));
        }

    }

}
