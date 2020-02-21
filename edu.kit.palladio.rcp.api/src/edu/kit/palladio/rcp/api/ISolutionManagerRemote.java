package edu.kit.palladio.rcp.api;

import java.rmi.RemoteException;

public interface ISolutionManagerRemote {
	void getSolution(final String launchId) throws RemoteException;

}
