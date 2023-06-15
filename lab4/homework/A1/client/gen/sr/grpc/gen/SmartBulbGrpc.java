package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: smarthome.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SmartBulbGrpc {

  private SmartBulbGrpc() {}

  public static final String SERVICE_NAME = "smarthome.SmartBulb";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.Empty> getTurnOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOn",
      requestType = sr.grpc.gen.Empty.class,
      responseType = sr.grpc.gen.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.Empty> getTurnOnMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Empty, sr.grpc.gen.Empty> getTurnOnMethod;
    if ((getTurnOnMethod = SmartBulbGrpc.getTurnOnMethod) == null) {
      synchronized (SmartBulbGrpc.class) {
        if ((getTurnOnMethod = SmartBulbGrpc.getTurnOnMethod) == null) {
          SmartBulbGrpc.getTurnOnMethod = getTurnOnMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Empty, sr.grpc.gen.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SmartBulbMethodDescriptorSupplier("TurnOn"))
              .build();
        }
      }
    }
    return getTurnOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.Empty> getTurnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TurnOff",
      requestType = sr.grpc.gen.Empty.class,
      responseType = sr.grpc.gen.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.Empty> getTurnOffMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Empty, sr.grpc.gen.Empty> getTurnOffMethod;
    if ((getTurnOffMethod = SmartBulbGrpc.getTurnOffMethod) == null) {
      synchronized (SmartBulbGrpc.class) {
        if ((getTurnOffMethod = SmartBulbGrpc.getTurnOffMethod) == null) {
          SmartBulbGrpc.getTurnOffMethod = getTurnOffMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Empty, sr.grpc.gen.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TurnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new SmartBulbMethodDescriptorSupplier("TurnOff"))
              .build();
        }
      }
    }
    return getTurnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.SmartBulbStateMessage> getGetStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetState",
      requestType = sr.grpc.gen.Empty.class,
      responseType = sr.grpc.gen.SmartBulbStateMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Empty,
      sr.grpc.gen.SmartBulbStateMessage> getGetStateMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Empty, sr.grpc.gen.SmartBulbStateMessage> getGetStateMethod;
    if ((getGetStateMethod = SmartBulbGrpc.getGetStateMethod) == null) {
      synchronized (SmartBulbGrpc.class) {
        if ((getGetStateMethod = SmartBulbGrpc.getGetStateMethod) == null) {
          SmartBulbGrpc.getGetStateMethod = getGetStateMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Empty, sr.grpc.gen.SmartBulbStateMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.SmartBulbStateMessage.getDefaultInstance()))
              .setSchemaDescriptor(new SmartBulbMethodDescriptorSupplier("GetState"))
              .build();
        }
      }
    }
    return getGetStateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmartBulbStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartBulbStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartBulbStub>() {
        @java.lang.Override
        public SmartBulbStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartBulbStub(channel, callOptions);
        }
      };
    return SmartBulbStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartBulbBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartBulbBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartBulbBlockingStub>() {
        @java.lang.Override
        public SmartBulbBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartBulbBlockingStub(channel, callOptions);
        }
      };
    return SmartBulbBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmartBulbFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartBulbFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartBulbFutureStub>() {
        @java.lang.Override
        public SmartBulbFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartBulbFutureStub(channel, callOptions);
        }
      };
    return SmartBulbFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void turnOn(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOnMethod(), responseObserver);
    }

    /**
     */
    default void turnOff(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTurnOffMethod(), responseObserver);
    }

    /**
     */
    default void getState(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.SmartBulbStateMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SmartBulb.
   */
  public static abstract class SmartBulbImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SmartBulbGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SmartBulb.
   */
  public static final class SmartBulbStub
      extends io.grpc.stub.AbstractAsyncStub<SmartBulbStub> {
    private SmartBulbStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartBulbStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartBulbStub(channel, callOptions);
    }

    /**
     */
    public void turnOn(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void turnOff(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getState(sr.grpc.gen.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.SmartBulbStateMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SmartBulb.
   */
  public static final class SmartBulbBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SmartBulbBlockingStub> {
    private SmartBulbBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartBulbBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartBulbBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.Empty turnOn(sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.Empty turnOff(sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTurnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.SmartBulbStateMessage getState(sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SmartBulb.
   */
  public static final class SmartBulbFutureStub
      extends io.grpc.stub.AbstractFutureStub<SmartBulbFutureStub> {
    private SmartBulbFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartBulbFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartBulbFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.Empty> turnOn(
        sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.Empty> turnOff(
        sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTurnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.SmartBulbStateMessage> getState(
        sr.grpc.gen.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TURN_ON = 0;
  private static final int METHODID_TURN_OFF = 1;
  private static final int METHODID_GET_STATE = 2;

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
        case METHODID_TURN_ON:
          serviceImpl.turnOn((sr.grpc.gen.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Empty>) responseObserver);
          break;
        case METHODID_TURN_OFF:
          serviceImpl.turnOff((sr.grpc.gen.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Empty>) responseObserver);
          break;
        case METHODID_GET_STATE:
          serviceImpl.getState((sr.grpc.gen.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.SmartBulbStateMessage>) responseObserver);
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
          getTurnOnMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Empty,
              sr.grpc.gen.Empty>(
                service, METHODID_TURN_ON)))
        .addMethod(
          getTurnOffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Empty,
              sr.grpc.gen.Empty>(
                service, METHODID_TURN_OFF)))
        .addMethod(
          getGetStateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Empty,
              sr.grpc.gen.SmartBulbStateMessage>(
                service, METHODID_GET_STATE)))
        .build();
  }

  private static abstract class SmartBulbBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartBulbBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.SmartHomeProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartBulb");
    }
  }

  private static final class SmartBulbFileDescriptorSupplier
      extends SmartBulbBaseDescriptorSupplier {
    SmartBulbFileDescriptorSupplier() {}
  }

  private static final class SmartBulbMethodDescriptorSupplier
      extends SmartBulbBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartBulbMethodDescriptorSupplier(String methodName) {
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
      synchronized (SmartBulbGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmartBulbFileDescriptorSupplier())
              .addMethod(getTurnOnMethod())
              .addMethod(getTurnOffMethod())
              .addMethod(getGetStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
