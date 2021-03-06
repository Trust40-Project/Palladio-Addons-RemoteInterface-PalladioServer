package edu.kit.palladio.rcp.api;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import org.prolog4j.Solution;

public interface ISolutionManagerRemote extends Remote {
	Map<String,Serializable> getSolution(final String launchId) throws RemoteException, IllegalStateException, NullPointerException;

}
