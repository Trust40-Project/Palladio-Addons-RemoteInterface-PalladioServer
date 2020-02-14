package edu.kit.palladio.rmi.querymanagement;

import java.rmi.Remote;
import java.util.Collection;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;

public interface IQueryManagerRMI extends Remote {
	Collection<QueryInformation> getQueries();
}
