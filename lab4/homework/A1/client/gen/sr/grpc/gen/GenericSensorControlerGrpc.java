package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: smarthome.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GenericSensorControlerGrpc {

  private GenericSensorControlerGrpc() {}

  public static final String SERVICE_NAME = "smarthome.GenericSensorControler";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerSubArg,
      sr.grpc.gen.GenericSensorControlerSensorValue> getSubReadingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubReadings",
      requestType = sr.grpc.gen.GenericSensorControlerSubArg.class,
      responseType = sr.grpc.gen.GenericSensorControlerSensorValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerSubArg,
      sr.grpc.gen.GenericSensorControlerSensorValue> getSubReadingsMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerSubArg, sr.grpc.gen.GenericSensorControlerSensorValue> getSubReadingsMethod;
    if ((getSubReadingsMethod = GenericSensorControlerGrpc.getSubReadingsMethod) == null) {
      synchronized (GenericSensorControlerGrpc.class) {
        if ((getSubReadingsMethod = GenericSensorControlerGrpc.getSubReadingsMethod) == null) {
          GenericSensorControlerGrpc.getSubReadingsMethod = getSubReadingsMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.GenericSensorControlerSubArg, sr.grpc.gen.GenericSensorControlerSensorValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubReadings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerSubArg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerSensorValue.getDefaultInstance()))
              .setSchemaDescriptor(new GenericSensorControlerMethodDescriptorSupplier("SubReadings"))
              .build();
        }
      }
    }
    return getSubReadingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerGetArg,
      sr.grpc.gen.GenericSensorControlerSensorValue> getGetReadingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReadings",
      requestType = sr.grpc.gen.GenericSensorControlerGetArg.class,
      responseType = sr.grpc.gen.GenericSensorControlerSensorValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerGetArg,
      sr.grpc.gen.GenericSensorControlerSensorValue> getGetReadingsMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerGetArg, sr.grpc.gen.GenericSensorControlerSensorValue> getGetReadingsMethod;
    if ((getGetReadingsMethod = GenericSensorControlerGrpc.getGetReadingsMethod) == null) {
      synchronized (GenericSensorControlerGrpc.class) {
        if ((getGetReadingsMethod = GenericSensorControlerGrpc.getGetReadingsMethod) == null) {
          GenericSensorControlerGrpc.getGetReadingsMethod = getGetReadingsMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.GenericSensorControlerGetArg, sr.grpc.gen.GenericSensorControlerSensorValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReadings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerGetArg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerSensorValue.getDefaultInstance()))
              .setSchemaDescriptor(new GenericSensorControlerMethodDescriptorSupplier("GetReadings"))
              .build();
        }
      }
    }
    return getGetReadingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.GenericSensorControlerDescribeSensors> getDescribeSensorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DescribeSensors",
      requestType = sr.grpc.gen.Empty.class,
      responseType = sr.grpc.gen.GenericSensorControlerDescribeSensors.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.GenericSensorControlerDescribeSensors> getDescribeSensorsMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Empty, sr.grpc.gen.GenericSensorControlerDescribeSensors> getDescribeSensorsMethod;
    if ((getDescribeSensorsMethod = GenericSensorControlerGrpc.getDescribeSensorsMethod) == null) {
      synchronized (GenericSensorControlerGrpc.class) {
        if ((getDescribeSensorsMethod = GenericSensorControlerGrpc.getDescribeSensorsMethod) == null) {
          GenericSensorControlerGrpc.getDescribeSensorsMethod = getDescribeSensorsMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Empty, sr.grpc.gen.GenericSensorControlerDescribeSensors>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DescribeSensors"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerDescribeSensors.getDefaultInstance()))
              .setSchemaDescriptor(new GenericSensorControlerMethodDescriptorSupplier("DescribeSensors"))
              .build();
        }
      }
    }
    return getDescribeSensorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerDescArg,
      sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> getDescribeSensorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DescribeSensor",
      requestType = sr.grpc.gen.GenericSensorControlerDescArg.class,
      responseType = sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerDescArg,
      sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> getDescribeSensorMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.GenericSensorControlerDescArg, sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> getDescribeSensorMethod;
    if ((getDescribeSensorMethod = GenericSensorControlerGrpc.getDescribeSensorMethod) == null) {
      synchronized (GenericSensorControlerGrpc.class) {
        if ((getDescribeSensorMethod = GenericSensorControlerGrpc.getDescribeSensorMethod) == null) {
          GenericSensorControlerGrpc.getDescribeSensorMethod = getDescribeSensorMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.GenericSensorControlerDescArg, sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DescribeSensor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerDescArg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription.getDefaultInstance()))
              .setSchemaDescriptor(new GenericSensorControlerMethodDescriptorSupplier("DescribeSensor"))
              .build();
        }
      }
    }
    return getDescribeSensorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenericSensorControlerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerStub>() {
        @java.lang.Override
        public GenericSensorControlerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericSensorControlerStub(channel, callOptions);
        }
      };
    return GenericSensorControlerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenericSensorControlerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerBlockingStub>() {
        @java.lang.Override
        public GenericSensorControlerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericSensorControlerBlockingStub(channel, callOptions);
        }
      };
    return GenericSensorControlerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenericSensorControlerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenericSensorControlerFutureStub>() {
        @java.lang.Override
        public GenericSensorControlerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenericSensorControlerFutureStub(channel, callOptions);
        }
      };
    return GenericSensorControlerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subReadings(sr.grpc.gen.GenericSensorControlerSubArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubReadingsMethod(), responseObserver);
    }

    /**
     */
    default void getReadings(sr.grpc.gen.GenericSensorControlerGetArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReadingsMethod(), responseObserver);
    }

    /**
     */
    default void describeSensors(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDescribeSensorsMethod(), responseObserver);
    }

    /**
     */
    default void describeSensor(sr.grpc.gen.GenericSensorControlerDescArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDescribeSensorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GenericSensorControler.
   */
  public static abstract class GenericSensorControlerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenericSensorControlerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenericSensorControler.
   */
  public static final class GenericSensorControlerStub
      extends io.grpc.stub.AbstractAsyncStub<GenericSensorControlerStub> {
    private GenericSensorControlerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericSensorControlerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericSensorControlerStub(channel, callOptions);
    }

    /**
     */
    public void subReadings(sr.grpc.gen.GenericSensorControlerSubArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubReadingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReadings(sr.grpc.gen.GenericSensorControlerGetArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReadingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void describeSensors(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDescribeSensorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void describeSensor(sr.grpc.gen.GenericSensorControlerDescArg request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDescribeSensorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GenericSensorControler.
   */
  public static final class GenericSensorControlerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenericSensorControlerBlockingStub> {
    private GenericSensorControlerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericSensorControlerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericSensorControlerBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.GenericSensorControlerSensorValue> subReadings(
        sr.grpc.gen.GenericSensorControlerSubArg request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubReadingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.GenericSensorControlerSensorValue getReadings(sr.grpc.gen.GenericSensorControlerGetArg request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReadingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.GenericSensorControlerDescribeSensors describeSensors(sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDescribeSensorsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription describeSensor(sr.grpc.gen.GenericSensorControlerDescArg request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDescribeSensorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenericSensorControler.
   */
  public static final class GenericSensorControlerFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenericSensorControlerFutureStub> {
    private GenericSensorControlerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenericSensorControlerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenericSensorControlerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.GenericSensorControlerSensorValue> getReadings(
        sr.grpc.gen.GenericSensorControlerGetArg request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReadingsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.GenericSensorControlerDescribeSensors> describeSensors(
        sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDescribeSensorsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription> describeSensor(
        sr.grpc.gen.GenericSensorControlerDescArg request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDescribeSensorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUB_READINGS = 0;
  private static final int METHODID_GET_READINGS = 1;
  private static final int METHODID_DESCRIBE_SENSORS = 2;
  private static final int METHODID_DESCRIBE_SENSOR = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUB_READINGS:
          serviceImpl.subReadings((sr.grpc.gen.GenericSensorControlerSubArg) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue>) responseObserver);
          break;
        case METHODID_GET_READINGS:
          serviceImpl.getReadings((sr.grpc.gen.GenericSensorControlerGetArg) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerSensorValue>) responseObserver);
          break;
        case METHODID_DESCRIBE_SENSORS:
          serviceImpl.describeSensors((sr.grpc.gen.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors>) responseObserver);
          break;
        case METHODID_DESCRIBE_SENSOR:
          serviceImpl.describeSensor((sr.grpc.gen.GenericSensorControlerDescArg) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubReadingsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              sr.grpc.gen.GenericSensorControlerSubArg,
              sr.grpc.gen.GenericSensorControlerSensorValue>(
                service, METHODID_SUB_READINGS)))
        .addMethod(
          getGetReadingsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.GenericSensorControlerGetArg,
              sr.grpc.gen.GenericSensorControlerSensorValue>(
                service, METHODID_GET_READINGS)))
        .addMethod(
          getDescribeSensorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Empty,
              sr.grpc.gen.GenericSensorControlerDescribeSensors>(
                service, METHODID_DESCRIBE_SENSORS)))
        .addMethod(
          getDescribeSensorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.GenericSensorControlerDescArg,
              sr.grpc.gen.GenericSensorControlerDescribeSensors.SensorDescription>(
                service, METHODID_DESCRIBE_SENSOR)))
        .build();
  }

  private static abstract class GenericSensorControlerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenericSensorControlerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.SmartHomeProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenericSensorControler");
    }
  }

  private static final class GenericSensorControlerFileDescriptorSupplier
      extends GenericSensorControlerBaseDescriptorSupplier {
    GenericSensorControlerFileDescriptorSupplier() {}
  }

  private static final class GenericSensorControlerMethodDescriptorSupplier
      extends GenericSensorControlerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GenericSensorControlerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GenericSensorControlerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenericSensorControlerFileDescriptorSupplier())
              .addMethod(getSubReadingsMethod())
              .addMethod(getGetReadingsMethod())
              .addMethod(getDescribeSensorsMethod())
              .addMethod(getDescribeSensorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
