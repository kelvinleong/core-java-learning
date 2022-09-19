package grpc.server.interceptor;

import io.grpc.*;

public class ServerResponseInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        return next.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall<>(call) {
                    @Override
                    public void sendMessage(RespT message) {
                        System.out.println("Message being sent to client : [" + message + "]");
                        super.sendMessage(message);
                    }
                },
                headers);
    }

}
