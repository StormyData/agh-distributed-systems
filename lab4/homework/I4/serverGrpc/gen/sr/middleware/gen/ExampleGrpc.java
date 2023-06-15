package sr.middleware.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: example.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ExampleGrpc {

  private ExampleGrpc() {}

  public static final String SERVICE_NAME = "example.Example";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithOptionalField,
      sr.middleware.gen.MessageReturned> getMethodWithOptionalArgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MethodWithOptionalArgs",
      requestType = sr.middleware.gen.MessageWithOptionalField.class,
      responseType = sr.middleware.gen.MessageReturned.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithOptionalField,
      sr.middleware.gen.MessageReturned> getMethodWithOptionalArgsMethod() {
    io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithOptionalField, sr.middleware.gen.MessageReturned> getMethodWithOptionalArgsMethod;
    if ((getMethodWithOptionalArgsMethod = ExampleGrpc.getMethodWithOptionalArgsMethod) == null) {
      synchronized (ExampleGrpc.class) {
        if ((getMethodWithOptionalArgsMethod = ExampleGrpc.getMethodWithOptionalArgsMethod) == null) {
          ExampleGrpc.getMethodWithOptionalArgsMethod = getMethodWithOptionalArgsMethod =
              io.grpc.MethodDescriptor.<sr.middleware.gen.MessageWithOptionalField, sr.middleware.gen.MessageReturned>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MethodWithOptionalArgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.middleware.gen.MessageWithOptionalField.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.middleware.gen.MessageReturned.getDefaultInstance()))
              .setSchemaDescriptor(new ExampleMethodDescriptorSupplier("MethodWithOptionalArgs"))
              .build();
        }
      }
    }
    return getMethodWithOptionalArgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithoutOptionalField,
      sr.middleware.gen.MessageReturned> getMethodWithoutOptionalArgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MethodWithoutOptionalArgs",
      requestType = sr.middleware.gen.MessageWithoutOptionalField.class,
      responseType = sr.middleware.gen.MessageReturned.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithoutOptionalField,
      sr.middleware.gen.MessageReturned> getMethodWithoutOptionalArgsMethod() {
    io.grpc.MethodDescriptor<sr.middleware.gen.MessageWithoutOptionalField, sr.middleware.gen.MessageReturned> getMethodWithoutOptionalArgsMethod;
    if ((getMethodWithoutOptionalArgsMethod = ExampleGrpc.getMethodWithoutOptionalArgsMethod) == null) {
      synchronized (ExampleGrpc.class) {
        if ((getMethodWithoutOptionalArgsMethod = ExampleGrpc.getMethodWithoutOptionalArgsMethod) == null) {
          ExampleGrpc.getMethodWithoutOptionalArgsMethod = getMethodWithoutOptionalArgsMethod =
              io.grpc.MethodDescriptor.<sr.middleware.gen.MessageWithoutOptionalField, sr.middleware.gen.MessageReturned>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MethodWithoutOptionalArgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.middleware.gen.MessageWithoutOptionalField.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.middleware.gen.MessageReturned.getDefaultInstance()))
              .setSchemaDescriptor(new ExampleMethodDescriptorSupplier("MethodWithoutOptionalArgs"))
              .build();
        }
      }
    }
    return getMethodWithoutOptionalArgsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExampleStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleStub>() {
        @java.lang.Override
        public ExampleStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleStub(channel, callOptions);
        }
      };
    return ExampleStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExampleBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleBlockingStub>() {
        @java.lang.Override
        public ExampleBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleBlockingStub(channel, callOptions);
        }
      };
    return ExampleBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExampleFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExampleFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExampleFutureStub>() {
        @java.lang.Override
        public ExampleFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExampleFutureStub(channel, callOptions);
        }
      };
    return ExampleFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void methodWithOptionalArgs(sr.middleware.gen.MessageWithOptionalField request,
        io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMethodWithOptionalArgsMethod(), responseObserver);
    }

    /**
     */
    default void methodWithoutOptionalArgs(sr.middleware.gen.MessageWithoutOptionalField request,
        io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMethodWithoutOptionalArgsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Example.
   */
  public static abstract class ExampleImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ExampleGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Example.
   */
  public static final class ExampleStub
      extends io.grpc.stub.AbstractAsyncStub<ExampleStub> {
    private ExampleStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleStub(channel, callOptions);
    }

    /**
     */
    public void methodWithOptionalArgs(sr.middleware.gen.MessageWithOptionalField request,
        io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMethodWithOptionalArgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void methodWithoutOptionalArgs(sr.middleware.gen.MessageWithoutOptionalField request,
        io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMethodWithoutOptionalArgsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Example.
   */
  public static final class ExampleBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ExampleBlockingStub> {
    private ExampleBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.middleware.gen.MessageReturned methodWithOptionalArgs(sr.middleware.gen.MessageWithOptionalField request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMethodWithOptionalArgsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.middleware.gen.MessageReturned methodWithoutOptionalArgs(sr.middleware.gen.MessageWithoutOptionalField request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMethodWithoutOptionalArgsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Example.
   */
  public static final class ExampleFutureStub
      extends io.grpc.stub.AbstractFutureStub<ExampleFutureStub> {
    private ExampleFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExampleFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExampleFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.middleware.gen.MessageReturned> methodWithOptionalArgs(
        sr.middleware.gen.MessageWithOptionalField request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMethodWithOptionalArgsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.middleware.gen.MessageReturned> methodWithoutOptionalArgs(
        sr.middleware.gen.MessageWithoutOptionalField request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMethodWithoutOptionalArgsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_METHOD_WITH_OPTIONAL_ARGS = 0;
  private static final int METHODID_METHOD_WITHOUT_OPTIONAL_ARGS = 1;

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
        case METHODID_METHOD_WITH_OPTIONAL_ARGS:
          serviceImpl.methodWithOptionalArgs((sr.middleware.gen.MessageWithOptionalField) request,
              (io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned>) responseObserver);
          break;
        case METHODID_METHOD_WITHOUT_OPTIONAL_ARGS:
          serviceImpl.methodWithoutOptionalArgs((sr.middleware.gen.MessageWithoutOptionalField) request,
              (io.grpc.stub.StreamObserver<sr.middleware.gen.MessageReturned>) responseObserver);
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
          getMethodWithOptionalArgsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.middleware.gen.MessageWithOptionalField,
              sr.middleware.gen.MessageReturned>(
                service, METHODID_METHOD_WITH_OPTIONAL_ARGS)))
        .addMethod(
          getMethodWithoutOptionalArgsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.middleware.gen.MessageWithoutOptionalField,
              sr.middleware.gen.MessageReturned>(
                service, METHODID_METHOD_WITHOUT_OPTIONAL_ARGS)))
        .build();
  }

  private static abstract class ExampleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExampleBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.middleware.gen.ExampleProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Example");
    }
  }

  private static final class ExampleFileDescriptorSupplier
      extends ExampleBaseDescriptorSupplier {
    ExampleFileDescriptorSupplier() {}
  }

  private static final class ExampleMethodDescriptorSupplier
      extends ExampleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ExampleMethodDescriptorSupplier(String methodName) {
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
      synchronized (ExampleGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExampleFileDescriptorSupplier())
              .addMethod(getMethodWithOptionalArgsMethod())
              .addMethod(getMethodWithoutOptionalArgsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
