package edu.kit.palladio.rcp;

/*import edu.kit.palladio.rmi.projectmanagment.IProjectManager;
import edu.kit.palladio.rmi.projectmanagment.ProjectManager;*/

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import edu.kit.palladio.rcpapi.ILoadMe;

/**
 * This class controls all aspects of the application's execution
 */
public class PalladioRCPApplication implements IApplication {

	private final AtomicBoolean terminationFlag = new AtomicBoolean(false);
	private Registry registry = null;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		
		synchronized (terminationFlag) {
			terminationFlag.set(false);
		}
		
		
		
		registry = LocateRegistry.createRegistry(10099);
		
		//TODO: Is this a good way to go? https://stackoverflow.com/questions/559989/how-do-i-get-the-osgi-bundlecontext-for-an-eclipse-rcp-application
		BundleContext bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
		//BundleContext bundleContext = InternalPlatform.getDefault().getBundleContext();
		ServiceReference<IComponentLoader> componentLoaderReference = bundleContext.getServiceReference(IComponentLoader.class);
		IComponentLoader componentLoader = bundleContext.getService(componentLoaderReference);
		for(IComponentInformation component: componentLoader.getComponents()) {
			System.out.println(component.getComponent().getRmiId());
			ILoadMe componentStub = (ILoadMe) UnicastRemoteObject.exportObject(component.getComponent(), 0);
			registry.bind(component.getComponent().getRmiId(), componentStub);
		}
		/*
		
		IProjectManager projectManagerStub = (IProjectManager) UnicastRemoteObject.exportObject(new ProjectManager(), 0);
		registry.bind(IProjectManager.class.getName(), projectManagerStub);*/
		
		/*IRemoteFileUpload fileUploadStub = (IRemoteFileUpload) UnicastRemoteObject.exportObject(new RemoteFileUpload(), 0);
		registry.bind(IRemoteFileUpload.class.getName(), fileUploadStub);*/
		

		System.out.println("RMI server running");
		
		
		
		//start rmi
		/*if(System.getSecurityManager() == null) {
			
			System.out.println("line 21");
			System.setSecurityManager(new SecurityManager());
			System.out.println("line 23");
		}*/
		/*System.out.println("line 25");
		try {
			*/
			//IHelloWorldFromEclipse stub = (IHelloWorldFromEclipse) UnicastRemoteObject.exportObject(new HelloWorldFromEclipse(), 0);
			/*HelloWorldFromEclipse impl = new HelloWorldFromEclipse();
			System.out.println("line 33");
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			System.out.println("line 35");
			//registry.bind("IHelloWorldFromEclipse", stub);
			
			String hostname = InetAddress.getLocalHost().getHostName();
			String rmiName = "//" + hostname + "/IHelloWorldFromEclipse";
			System.out.println("Bindinghostname: " + hostname);
			System.out.println("line 41");
			Naming.rebind(rmiName, impl);
			 */		
			//System.setProperty("java.rmi.server.hostname","127.0.0.1");
			//System.setProperty("java.rmi.server.hostname","0.0.4.75");
			/*
			registry = LocateRegistry.createRegistry(10099);
			String name = "HelloEclipse";*/
			//engine = new HelloWorldFromEclipse();
			/*System.out.println("line 29");
			stub = (IHelloWorldFromEclipse) UnicastRemoteObject.exportObject(engine, 10099);
			System.out.println("line 31");*/
			//registry = LocateRegistry.getRegistry();
			
			
			
			//registry.bind(name, engine);
			//registry.bind(name, stub);
			/*registry.rebind("HelloWorldImpl", engine);
			System.out.println("line 53");
			registry.rebind(name, stub);
			System.out.println("ComputeEngine bound");
			if(System.getSecurityManager() == null) {
				System.out.println("line 21");
				System.setSecurityManager(new SecurityManager());
				System.out.println("line 23");
			}
			while(true) {
				
			}
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }*/

		synchronized (terminationFlag) {
			while (!terminationFlag.get()) {
				terminationFlag.wait();
			}
		}
		/*Ensure that all projects are closed an thus saved to the file system.*/
		//TODO: what if close fails?
		//projectManagerStub.close();
			
		
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		synchronized (terminationFlag) {
			terminationFlag.set(true);
			terminationFlag.notifyAll();
		}
	}
}
