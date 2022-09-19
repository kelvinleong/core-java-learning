package grpc.client.interceptor;

import io.grpc.*;

public class RequestInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions,
                                                               Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<>(
                channel.newCall(methodDescriptor, callOptions)) {
            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                headers.put(Metadata.Key.of("JWT", Metadata.ASCII_STRING_MARSHALLER), "token");
                super.start(responseListener, headers);
            }
        };
    }
}
