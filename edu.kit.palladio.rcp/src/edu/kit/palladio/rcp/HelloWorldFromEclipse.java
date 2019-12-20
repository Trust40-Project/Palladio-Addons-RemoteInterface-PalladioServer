package edu.kit.palladio.rcp;

import java.rmi.RemoteException;
import java.util.Date;

public class HelloWorldFromEclipse implements IHelloWorldFromEclipse {

	@Override
	public String hello(String whatToSay) throws RemoteException {
		Date timeArrived = new Date();
		System.out.println("Hello from eclipse with message: "+whatToSay);
		return "eclipse said "+ whatToSay+ " at " + timeArrived.toString();
	}

}
