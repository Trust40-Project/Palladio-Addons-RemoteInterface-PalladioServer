package edu.kit.palladio.remote.resultmanagement;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Future;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisBlackboard;

public interface IResultManager {
	void registerFutureSolution(final String launchId, Future<Serializable> futureSolution);
	Serializable getSolution(final String launchId) throws IllegalStateException, NullPointerException;
}
