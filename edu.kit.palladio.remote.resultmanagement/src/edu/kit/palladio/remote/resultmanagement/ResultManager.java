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
	public Serializable getSolution(final String launchId) throws IllegalStateException, NullPointerException {
		final Future<Serializable> futureSolution = this.registeredFutureSolutions.get(launchId);
		if (futureSolution == null) {
			throw new NullPointerException("No solution for the launch id " + launchId + " could be found.");
		}
		if (futureSolution.isCancelled()) {
			throw new IllegalStateException("The launch was canceled. Please try launching it again.");
		}
		if (!futureSolution.isDone()) {
			throw new IllegalStateException("The launch is not yet done.");
		}
		try {
			return futureSolution.get(MAXNUMMILLISECTOWAIT, TimeUnit.MILLISECONDS);

		} catch (InterruptedException e) {
			throw new IllegalStateException("Could not get solution for launch. Please try again.");
		} catch (ExecutionException e) {
			throw new IllegalStateException("Launch could not get execute because of " + e.getLocalizedMessage()
					+ " Please fix the problem and try launching again.");
		} catch (TimeoutException e) {
			throw new IllegalStateException("Could not get solution for launch. Please try again.");
		}
	}

}