package edu.kit.palladio.rmi.parallelanalysismanagment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflow;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class LaunchManager implements ILaunchManager {
	private static ExecutorService executor;
	
	
	public LaunchManager(){
		ExecutorService currentExecutor = LaunchManager.executor;
		if (currentExecutor == null) {
			synchronized (LaunchManager.class) {
				currentExecutor = LaunchManager.executor;
				if (currentExecutor == null) {
					LaunchManager.executor = Executors.newCachedThreadPool();
				}
			}
		}
		
	}
	

	@Override
	public void addLaunch(AnalysisWorkflow analysisWorkflow) {
		executor.execute(() -> {
			try {
				analysisWorkflow.execute(new NullProgressMonitor());
			} catch (JobFailedException | UserCanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}



}
