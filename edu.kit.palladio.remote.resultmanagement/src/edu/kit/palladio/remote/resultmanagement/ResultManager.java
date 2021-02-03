package edu.kit.palladio.remote.resultmanagement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(immediate = true, property = { "id=edu.kit.palladio.remote.resultmanagement.resultmanager",
		"name=Result Manager" }, scope = ServiceScope.SINGLETON)
public class ResultManager implements IResultManager {

	private final static int MAXNUMMILLISECTOWAIT = 100;
	private Map<String, Future<Serializable>> registeredFutureSolutions;

	public ResultManager() {
		this.registeredFutureSolutions = new HashMap<String, Future<Serializable>>();
	}

	@Override
	public void registerFutureSolution(String launchId, Future<Serializable> futureSolution) {
		this.registeredFutureSolutions.put(launchId, futureSolution);
	}

	@Override
	public Future<Serializable> getSolution(final String launchId) {
		return this.registeredFutureSolutions.get(launchId);
	
	}

}