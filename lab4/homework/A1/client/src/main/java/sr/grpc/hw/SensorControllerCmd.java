package sr.grpc.hw;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SensorControllerCmd implements IServiceCmd {
    private final Map<String, Map<Integer, Sensor>> sensors = new HashMap<>();

    private static Integer getId(String[] args) {
        return Util.getParameter(Arrays.stream(args), "id=")
                .map(Util::parseIntOrNull)
                .orElse(null);

    }

    @Override
    public void get(String server, String[] args, GrpcClient client) {
        Integer id = getId(args);
        if (id == null) {
            System.out.println("missing or invalid id");
            return;
        }
        Sensor sensor = fetchSensor(server, id, client);
        System.out.printf("Sensor value = %f%s\n", client.getSensorValue(sensor), sensor.unit());
    }

    @Override
    public void describe(String server, String[] args, GrpcClient client) {
        Integer id = getId(args);
        List<Sensor> sensorList;
        if (id == null) {
            sensorList = fetchAllSensors(server, client);
        } else {
            sensorList = List.of(fetchSensor(server, id, client));
        }
        for (Sensor sensor :
                sensorList) {
            System.out.println(sensor);
        }
        if (sensorList.isEmpty()) {
            System.out.println("this server has no registered sensors");
        }
    }

    @Override
    public void sub(String server, String[] args, GrpcClient client) {
        Integer id = getId(args);
        if (id == null) {
            System.out.println("missing or invalid id");
            return;
        }
        Integer refresh = Util.getParameter(Arrays.stream(args), "refresh=")
                .map(Util::parseIntOrNull)
                .orElse(null);
        if (refresh == null)
            refresh = 1000;
        Sensor sensor = fetchSensor(server, id, client);
        var channelId = client.subSensorValue(sensor, refresh,
                value -> System.out.printf("Sensor value = %f%s\n", value, sensor.unit()));
        System.out.printf("Subscribed to sensor value with channelId=%d\n", channelId);
    }

    @Override
    public void help() {
        System.out.println("""
                this service exposes values measured by many connected sernsors
                get id=<sensor id> <- displays the value of a sensor with given id
                describe [id=<sensor-id>] <- displays a description of the selected/all of the sensors on the server
                sub id=<sensor-id> [refresh=<max refresh rate in ms>] <- subscribes to the sensor value and sets the
                    max refresh rate to refresh (default 1000ms)""");
    }

    private Sensor fetchSensor(String server, int id, GrpcClient client) {
        return sensors
                .computeIfAbsent(server, key -> new HashMap<>())
                .computeIfAbsent(id, key -> client.getSensorDescription(server, key));
    }

    private List<Sensor> fetchAllSensors(String server, GrpcClient client) {
        List<Sensor> ret = client.getAllSensorsOnServer(server);
        sensors.computeIfAbsent(server, key -> new HashMap<>())
                .putAll(ret.stream().collect(Collectors.toMap(Sensor::id, Function.identity())));
        return ret;
    }

}
