package sr.grpc.hw;

public class Main {
    public static void main(String[] args) {
        GrpcClient client = new GrpcClient();
        CmdLine cmdLine = new CmdLine(client);
        cmdLine.run();
    }
}