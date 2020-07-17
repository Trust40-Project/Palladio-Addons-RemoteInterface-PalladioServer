package edu.kit.palladio.proto.rest;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class ChannelFactory implements DisposableBean {
    private final ManagedChannel channel;
    private final static String TARGET = "localhost:50051";

    public ChannelFactory() {
        channel = ManagedChannelBuilder.forTarget(TARGET).usePlaintext().build();
    }

    public ManagedChannel getChannel(){
        return channel;
    }

    @Override
    public void destroy() {
        // ManagedChannels use resources like threads and TCP connections. To prevent
        // leaking these
        // resources the channel should be shut down when it will no longer be used. If
        // it may be used
        // again leave it running.
        try {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}