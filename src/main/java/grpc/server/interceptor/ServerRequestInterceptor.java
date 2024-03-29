package grpc.server.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ServerRequestInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        System.out.println("Validating user token");
        var userToken = headers.get(Metadata.Key.of("JWT", Metadata.ASCII_STRING_MARSHALLER));
        validateUserToken(userToken);
        return next.startCall(call, headers);
    }

    private void validateUserToken(String userToken) {
        // Logic to validate token
    }
}
