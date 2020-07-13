package edu.kit.palladio.proto.rcp;


	import io.grpc.Server;
	import io.grpc.ServerBuilder;
	import java.io.IOException;
	import java.util.concurrent.TimeUnit;
	import java.util.logging.Logger;

import edu.kit.palladio.proto.projectmanagement.ProjectManagerService;

	/**
	 * Server that manages startup/shutdown of a {@code Greeter} server.
	 */
	public class GRPCServer {
	  private static final Logger logger = Logger.getLogger(GRPCServer.class.getName());

	  private Server server;

	  void start() throws IOException {
	    /* The port on which the server should run */
	    int port = 50051;
	    server = ServerBuilder.forPort(port)
	    		.addService(new ProjectManagerService())
	        .build()
	        .start();
	    logger.info("Server started, listening on " + port);
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	      @Override
	      public void run() {
	        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
	        System.err.println("*** shutting down gRPC server since JVM is shutting down");
	        try {
	        	GRPCServer.this.stop();
	        } catch (InterruptedException e) {
	          e.printStackTrace(System.err);
	        }
	        System.err.println("*** server shut down");
	      }
	    });
	  }

	 void stop() throws InterruptedException {
	    if (server != null) {
	      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
	    }
	  }

	  /**
	   * Await termination on the main thread since the grpc library uses daemon threads.
	   */
	  void blockUntilShutdown() throws InterruptedException {
	    if (server != null) {
	      server.awaitTermination();
	    }
	  }
}
