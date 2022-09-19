package grpc.server;

import grpc.Projection;
import grpc.ProjectionServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.UUID;


public class ProjectionService extends ProjectionServiceGrpc.ProjectionServiceImplBase {
    @Override
    public void getProjection(Projection.GetRequest request, StreamObserver<Projection.GetResponse> resp) {

        var name = request.getName();
        var data = Projection.Data.newBuilder()
                .setUuid(UUID.randomUUID().toString())
                .setName("Data Name")
                .setContent("Content")
                .build();

        var reply = Projection.GetResponse.newBuilder()
                .setName(name)
                .setData(data)
                .build();

        resp.onNext(reply);
        resp.onCompleted();
        System.out.println("sent a reply!");
    }
}
