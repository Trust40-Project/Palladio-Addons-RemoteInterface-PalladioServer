package edu.kit.palladio.rmi.querymanagement;

import java.rmi.RemoteException;
import java.util.Collection;

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
	public Collection<QueryInformation> getQueries(){
		return queryManagerInstance.getQueries().keySet();
	}

}
