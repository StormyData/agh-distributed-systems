package sr.grpc.hw;

public interface IServiceCmd {
    default void get(String server, String[] args, GrpcClient client) {
        System.out.println("this service doesn't support this method");
    }

    default void sub(String server, String[] args, GrpcClient client) {
        System.out.println("this service doesn't support this method");
    }

    default void set(String server, String[] args, GrpcClient client) {
        System.out.println("this service doesn't support this method");
    }

    default void describe(String server, String[] args, GrpcClient client) {
        System.out.println("this service doesn't support this method");
    }

    default void help() {
        System.out.println("this service doesn't have a help page");
    }
}
