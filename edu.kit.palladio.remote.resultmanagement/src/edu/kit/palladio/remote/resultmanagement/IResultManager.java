package edu.kit.palladio.remote.resultmanagement;

import java.io.Serializable;
import java.util.concurrent.Future;

public interface IResultManager {
	void registerFutureSolution(final String launchId, Future<Serializable> futureSolution);
	Future<Serializable> getSolution(final String launchId);
}
