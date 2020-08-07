package edu.kit.palladio.rmi.services.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Extend or implement this interface to be loaded into the RCP application to be 
 * available as an RMI service.
 */
public interface IRmiService extends Remote {
	String getRmiId() throws RemoteException;
}
