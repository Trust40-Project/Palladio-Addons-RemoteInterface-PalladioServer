package edu.kit.palladio.rcp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloWorldFromEclipse extends Remote {
	String hello(String whatToSay) throws RemoteException;

}
