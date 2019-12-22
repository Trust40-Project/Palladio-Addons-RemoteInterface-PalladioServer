package edu.kit.palladio.rcp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

public class HelloWorldFromEclipse implements IHelloWorldFromEclipse, Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public HelloWorldFromEclipse() {
		System.out.println("constructor of hello world from eclipse");
	}
	@Override
	public String hello(String whatToSay) throws RemoteException {
		Date timeArrived = new Date();
		//System.out.println("Hello from eclipse with message: "+whatToSay);
		return "eclipse said "+ whatToSay+ " at " + timeArrived.toString();
	}

}
