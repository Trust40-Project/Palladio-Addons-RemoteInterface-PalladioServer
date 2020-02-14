package edu.kit.palladio.rmi.supportingprolog4j;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProverManagerRMI extends Remote {
	List<ProverInformationSerializable> getProvers() throws RemoteException;
}
