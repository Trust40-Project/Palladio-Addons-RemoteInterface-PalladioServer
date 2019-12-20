package edu.kit.palladio.rmi.filemanagment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		//start rmi
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			String name = "FileUpload";
			IRemoteFileUpload fileUploadEngine = new RemoteFileUpload();
			IRemoteFileUpload fileUploadStub = (IRemoteFileUpload) UnicastRemoteObject.exportObject(fileUploadEngine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, fileUploadStub);
			System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
