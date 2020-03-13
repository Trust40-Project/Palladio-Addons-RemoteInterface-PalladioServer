package edu.kit.palladio.rcp.api;

import java.rmi.RemoteException;
import java.util.concurrent.Future;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisBlackboard;
import org.prolog4j.Solution;

public interface ISolutionManager {
	void registerFutureSolution(final String launchId, Future<AnalysisBlackboard> futureSolution);
}
