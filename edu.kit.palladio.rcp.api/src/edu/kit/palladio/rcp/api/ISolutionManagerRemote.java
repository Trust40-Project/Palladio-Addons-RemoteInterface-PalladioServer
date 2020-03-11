package edu.kit.palladio.rcp.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISolutionManagerRemote extends Remote {
	void getSolution(final String launchId) throws RemoteException;

}
