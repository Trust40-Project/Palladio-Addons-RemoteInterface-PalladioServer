package edu.kit.palladio.rmi.parallelanalysismanagment;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflow;

public interface ILaunchManager {
	public void addLaunch(AnalysisWorkflow analysisWorkflow);
}
