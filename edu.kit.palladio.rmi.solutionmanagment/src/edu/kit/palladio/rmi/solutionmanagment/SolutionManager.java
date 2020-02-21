package edu.kit.palladio.rmi.solutionmanagment;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.osgi.service.component.annotations.Component;
import org.prolog4j.Solution;

import edu.kit.palladio.rcp.api.ILoadMe;
import edu.kit.palladio.rcp.api.ISolutionManager;

@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.solutionmanagment.solutionmanager", "name=Solution Manager"})
public class SolutionManager implements ISolutionManager, ILoadMe {

	private final static int MAXNUMMILLISECTOWAIT = 100;
	private final static String RMIID = "edu.kit.palladio.rcp.api.ISolutionManagerRemote";
	private Map<String, Future<Solution<Object>>> registeredFutureSolutions;
	
	public SolutionManager() {
		this.registeredFutureSolutions = new HashMap<String, Future<Solution<Object>>>();
	}
	
	@Override
	public String getRmiId() throws RemoteException {
		return RMIID;
	}

	@Override
	public void registerFutureSolution(String launchId, Future<Solution<Object>> futureSolution) {
		this.registeredFutureSolutions.put(launchId, futureSolution);
	}

	@Override
	public void getSolution(String launchId) throws RemoteException {
		// TODO Auto-generated method stub
		Future<Solution<Object>> futureSolution = this.registeredFutureSolutions.get(launchId);
		if(futureSolution == null) {
			//TODO: Return that solution does not exist.
		}
		if(futureSolution.isCancelled()) {
			//TODO: Return response that future is cancelled yet.
		}
		if(!futureSolution.isDone()) {
			//TODO: Return response that future is not done yet.
		}
		
		try {
			Solution<Object> solution = futureSolution.get(MAXNUMMILLISECTOWAIT, TimeUnit.MILLISECONDS);
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
