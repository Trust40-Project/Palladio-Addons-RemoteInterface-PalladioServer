package edu.kit.palladio.rmi.querymanagement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;

public interface IQueryManagerRMI extends Remote {
	List<QueryInformationSerializable> getQueries() throws RemoteException;
}
