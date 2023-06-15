package sr.middleware.grpc;
import io.grpc.stub.StreamObserver;
import sr.middleware.gen.ExampleGrpc.ExampleImplBase;
import sr.middleware.gen.MessageReturned;
import sr.middleware.gen.MessageWithOptionalField;
import sr.middleware.gen.MessageWithoutOptionalField;

public class ExampleImpl extends ExampleImplBase {

    @Override
    public void methodWithOptionalArgs(MessageWithOptionalField request, StreamObserver<MessageReturned> responseObserver) {
        int required = request.getArg2();
        if(request.hasArg1())
        {
            int optional = request.getArg1();
            responseObserver.onNext(MessageReturned.newBuilder().setReturn(optional + required).build());
        }else {
            responseObserver.onNext(MessageReturned.newBuilder().setReturn(1000 + required).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void methodWithoutOptionalArgs(MessageWithoutOptionalField request, StreamObserver<MessageReturned> responseObserver) {
        int required = request.getArg2();
        int optional = request.getArg1();
        responseObserver.onNext(MessageReturned.newBuilder().setReturn(optional + required).build());
        responseObserver.onCompleted();
    }
}
