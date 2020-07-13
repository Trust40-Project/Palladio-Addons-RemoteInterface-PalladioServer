package edu.kit.palladio.rcp.proto;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import edu.kit.palladio.proto.filemanagement.HelloWorldServer;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		final HelloWorldServer server = new HelloWorldServer();
	    server.start();
	    server.blockUntilShutdown();
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do
	}
}
