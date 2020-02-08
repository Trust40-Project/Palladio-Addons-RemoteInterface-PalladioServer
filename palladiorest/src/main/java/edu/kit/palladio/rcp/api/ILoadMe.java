package edu.kit.palladio.rcp.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Extend or implement this interface to be loaded into the RCP application to be 
 * available as an RMI service.
 */
public interface ILoadMe extends Remote {
	String getRmiId() throws RemoteException;
}
