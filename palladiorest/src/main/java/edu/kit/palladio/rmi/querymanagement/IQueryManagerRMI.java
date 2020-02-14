package edu.kit.palladio.rmi.querymanagement;

import java.rmi.Remote;
import java.util.List;

public interface IQueryManagerRMI extends Remote {
	List<QueryInformationSerializable> getQueries();
}
