package edu.kit.palladio.rmi.supportingprolog4j;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.prolog4j.ProverInformation;

public interface IProverManagerRMI extends Remote {
	List<ProverInformationSerializable> getProvers() throws RemoteException;
}
