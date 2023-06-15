package sr.middleware.grpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

public class Main {
    public static void main(String[] args) {
        try
        {
            var socketAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.2"), 20000);
            var server = NettyServerBuilder
                    .forAddress(socketAddress)
                    .executor(Executors.newFixedThreadPool(1))
                    .addService(new ExampleImpl())
                    .build()
                    .start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                server.shutdown();
                System.err.println("*** server shut down");
            }));


            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}