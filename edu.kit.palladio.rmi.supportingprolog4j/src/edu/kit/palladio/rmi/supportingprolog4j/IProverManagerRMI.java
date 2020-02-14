package edu.kit.palladio.rmi.supportingprolog4j;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import org.prolog4j.IProverFactory;
import org.prolog4j.ProverInformation;

public interface IProverManagerRMI extends Remote {
	Collection<ProverInformation> getProvers() throws RemoteException;
}
