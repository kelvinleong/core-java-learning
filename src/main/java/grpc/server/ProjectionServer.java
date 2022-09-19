package grpc.server;

import grpc.server.interceptor.ServerRequestInterceptor;
import grpc.server.interceptor.ServerResponseInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProjectionServer {
    private final Server server;

    public ProjectionServer(int port) {
        var service = new ProjectionService();
        this.server = ServerBuilder.forPort(port)
                .addService(
                        ServerInterceptors.intercept(
                                service,
                                new ServerResponseInterceptor(),
                                new ServerRequestInterceptor()))
                .build();
    }

    public void start() throws IOException {
        System.out.println("Starting Server..");
        server.start();
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    try {
                        this.stop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
    }

    private void stop() throws InterruptedException {
        System.out.println("Stopping Server..");
        if (server != null) {
            server.shutdown().awaitTermination(300, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutDown() throws InterruptedException {
        if (this.server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var productServer = new ProjectionServer(3000);
        productServer.start();
        productServer.blockUntilShutDown();
    }
}
