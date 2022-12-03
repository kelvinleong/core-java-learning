package grpc.client;

import grpc.Projection;
import grpc.client.interceptor.RequestInterceptor;
import grpc.client.interceptor.ResponseInterceptor;
import io.grpc.CallOptions;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import static grpc.ProjectionServiceGrpc.getGetProjectionMethod;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;

public class AsyncClient {
    private final String host;
    private final int port;

    public AsyncClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void callServer() {
        System.out.println("Calling Server..");
        var managedChannel = ManagedChannelBuilder.forAddress(host, port)
                .intercept(
                        new RequestInterceptor(),
                        new ResponseInterceptor())
                .usePlaintext()
                .build();

        // Create a new async stub
        var projectionRequest = Projection.GetRequest.newBuilder().setName("p1").build();
        var call = managedChannel.newCall(getGetProjectionMethod(), CallOptions.DEFAULT);
        asyncServerStreamingCall(call, projectionRequest, new ProjectionCallback());
        System.out.println("Finished call");
    }

    private static class ProjectionCallback implements StreamObserver<Projection.GetResponse> {

        @Override
        public void onNext(Projection.GetResponse value) {
            System.out.println("Received projection: " + value);
        }

        @Override
        public void onError(Throwable cause) {
            System.out.println("Error occurred, cause: " + cause.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("Stream completed");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var client = new AsyncClient("0.0.0.0", 3000);
        client.callServer();
        Thread.sleep(30000);
    }
}
