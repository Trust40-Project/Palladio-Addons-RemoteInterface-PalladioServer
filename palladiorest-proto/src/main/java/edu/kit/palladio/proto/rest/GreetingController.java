package edu.kit.palladio.proto.rest;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


@RestController
public class GreetingController {


	@GetMapping("/greeting")
	public void greeting(@RequestParam(value = "name", defaultValue = "World") String name)
        throws InterruptedException {
        // Access a service running on the local machine on port 50051
        /*String target = "localhost:50051";
        
    
        // Create a communication channel to the server, known as a Channel. Channels are thread-safe
        // and reusable. It is common to create channels at the beginning of your application and reuse
        // them until the application shuts down.
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build();
        try {
          HelloWorldClient client = new HelloWorldClient(channel);
          client.greet(name);
        } finally {
          // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
          // resources the channel should be shut down when it will no longer be used. If it may be used
          // again leave it running.
          channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }*/
	}
}