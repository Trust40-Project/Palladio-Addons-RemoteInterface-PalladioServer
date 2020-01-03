package edu.kit.palladio.rcp;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class PalladioRCPApplication implements IApplication {

	private static final IHelloWorldFromEclipse engine = new HelloWorldFromEclipse();
	private static Registry registry = null;
	private static IHelloWorldFromEclipse stub = null;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		
		//ISimulationController stub = (ISimulationController) UnicastRemoteObject.exportObject(this, 0);
		stub = (IHelloWorldFromEclipse) UnicastRemoteObject.exportObject(engine, 0);

		registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

		registry.bind("IHelloWorldFromEclipse", stub);

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
		while(true) {
			
		}
		//return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do
	}
}
