package edu.kit.palladio.remote.resultmanagement;

import java.util.concurrent.Future;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisBlackboard;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.osgi.service.component.annotations.Component;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisBlackboard;
import org.prolog4j.Solution;
import org.prolog4j.SolutionIterator;


@Component(immediate = true, property = { "id=edu.kit.palladio.remote.resultmanagement.resultmanager", "name=Result Manager"})
public class ResultManager implements IResultManager{

	private final static int MAXNUMMILLISECTOWAIT = 100;
	private Map<String, Future<AnalysisBlackboard>> registeredFutureSolutions;
	
	public ResultManager() {
		this.registeredFutureSolutions = new HashMap<String, Future<AnalysisBlackboard>>();
	}
	

	@Override
	public void registerFutureSolution(String launchId, Future<AnalysisBlackboard> futureSolution) {
		this.registeredFutureSolutions.put(launchId, futureSolution);
	}

	@Override
	public Map<String,Serializable> getSolution(final String launchId) throws IllegalStateException, NullPointerException {
		final Future<AnalysisBlackboard> futureSolution = this.registeredFutureSolutions.get(launchId);
		if(futureSolution == null) {
			throw new NullPointerException("No solution for the launch id " + launchId + " could be found.");
		}
		if(futureSolution.isCancelled()) {
			throw new IllegalStateException("The launch was canceled. Please try launching it again.");
		}
		if(!futureSolution.isDone()) {
			throw new IllegalStateException("The launch is not yet done.");
		}
		
		try {
			final AnalysisBlackboard blackboard = futureSolution.get(MAXNUMMILLISECTOWAIT, TimeUnit.MILLISECONDS);
			final Solution<Object> solution = blackboard.getSolution();
			if(!solution.isSuccess()) {
				throw new IllegalStateException("The solution to the launch was not successful.");
			}
			final HashMap<String, Serializable> results = new HashMap<String, Serializable>();
			for (Entry<String, String> variable : blackboard.getQuery().getResultVars().entrySet()) {
				
				Object result = (Serializable) solution.get(variable.getValue());
				if(result instanceof Serializable) {
					results.put(variable.getKey(), (Serializable) result);
				} else {
					results.put(variable.getKey(), "Not serializable.");
				}
				
			}
			return results;
		} catch (InterruptedException e) {
			throw new IllegalStateException("Could not get solution for launch. Please try again.");
		} catch (ExecutionException e) {
			throw new IllegalStateException("Launch could not get execute because of " + e.getLocalizedMessage() + " Please fix the problem and try launching again.");
		} catch (TimeoutException e) {
			throw new IllegalStateException("Could not get solution for launch. Please try again.");
		}
	}


}