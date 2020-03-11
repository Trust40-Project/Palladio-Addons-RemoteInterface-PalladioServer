package edu.kit.palladio.rcp.api;

import java.rmi.RemoteException;
import java.util.concurrent.Future;

import org.prolog4j.Solution;

public interface ISolutionManager {
	void registerFutureSolution(final String launchId, Future<Solution<Object>> futureSolution);
}
