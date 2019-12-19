package edu.kit.palladio.rcp;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class PalladioRCPApplication implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Hello RCP World!");
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do
	}
}
