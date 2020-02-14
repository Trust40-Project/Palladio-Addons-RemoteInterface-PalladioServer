package edu.kit.palladio.rmi.querymanagement;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.impl.IQueryManager;

import edu.kit.palladio.rcp.api.ILoadMe;

@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI",
"name=Query Manager" })
public class QueryManagerWrapper implements IQueryManagerRMI, ILoadMe {

	@Reference(service = IQueryManager.class)
	private IQueryManager queryManagerInstance;
	
	private static final String RMIID = "edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI";
	
	@Override
	public String getRmiId() throws RemoteException {
		
		return RMIID;
	}
	
	@Override
	public List<QueryInformationSerializable> getQueries() throws RemoteException{
		List<QueryInformationSerializable> queryInformationList = new LinkedList<QueryInformationSerializable>();
		for(QueryInformation queryInformation : queryManagerInstance.getQueries().keySet()) {
			queryInformationList.add(new QueryInformationSerializable(queryInformation.getId(), queryInformation.getName()));
		}
		return queryInformationList;
	}

}
