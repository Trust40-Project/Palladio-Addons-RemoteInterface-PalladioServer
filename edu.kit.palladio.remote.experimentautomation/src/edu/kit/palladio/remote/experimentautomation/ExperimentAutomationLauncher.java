package edu.kit.palladio.remote.experimentautomation;

import de.uka.ipd.sdq.workflow.BlackboardBasedWorkflow;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import edu.kit.palladio.remote.resultmanagement.IResultManager;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.palladiosimulator.experimentautomation.application.config.ExperimentAutomationConfiguration;
import org.palladiosimulator.experimentautomation.application.jobs.RunExperimentAutomationJob;
import org.palladiosimulator.experimentautomation.application.utils.EcoreHelper;

@Component(immediate = true, property = { "id=edu.kit.palladio.remote.experimentautomation", "name=Experiment Automation Launcher"})
public class ExperimentAutomationLauncher implements IExperimentAutomationLauncher {
	
	private ExecutorService executor;
	private IResultManager solutionManager;
	
	@Reference
	public void setResultManager(IResultManager solutionManager) {
		this.solutionManager = solutionManager;
	}
	
	public ExperimentAutomationLauncher(){
		
		/*ExecutorService currentExecutor = ExperimentAutomationLauncher.executor;
		if (currentExecutor == null) {
			synchronized (ExperimentAutomationLauncher.class) {
				currentExecutor = ExperimentAutomationLauncher.executor;
				if (currentExecutor == null) {
					ExperimentAutomationLauncher.executor = Executors.newCachedThreadPool();
				}
			}
		}*/
	}

	@Override
	public String launch(IExperimentAutomationLaunchConfig launchConfig) throws IllegalArgumentException {
		// get location of experiments file
        final Path experimentsLocation = new Path(launchConfig.getExperimentsLocationPath());
         // load experiments
        final List<Experiment> experiments = getExperiments(experimentsLocation, launchConfig.getFilteredExperimentIds());
        final ExperimentAutomationConfiguration experimentAutomationConfiguration = new ExperimentAutomationConfiguration();
        experimentAutomationConfiguration.setExperiments(experiments);
        experimentAutomationConfiguration.setAttributes(new HashMap<String, Object>());

        
        
        Future<Serializable> futureSolution = executor.submit(() -> {
        	// run experiments via blackboard-based workflow
            final MDSDBlackboard blackboard = new MDSDBlackboard();
            final BlackboardBasedWorkflow<MDSDBlackboard> workflow = new BlackboardBasedWorkflow<MDSDBlackboard>(
                    new RunExperimentAutomationJob(experimentAutomationConfiguration), blackboard);
            workflow.run();
            
            System.out.println("blackboard of experiment automation: " + blackboard.toString());
            return "TODO figure out how to serialize Blackboard";
        });
        Instant now = Instant.now();
		 final String launchId = launchConfig.getLaunchName()+ "-" + now.toString();
		 solutionManager.registerFutureSolution(launchId, futureSolution);
		 
		 return launchId;
	}

	private static List<Experiment> getExperiments(final IPath experimentsLocation,
            final String[] filteredExperimentIDs) {
		final Bundle bundle = Activator.getContext().getBundle();
        final ResourceSet resourceSet = new ResourceSetImpl();
        final EClass expectedType = ExperimentsPackage.eINSTANCE.getExperimentRepository();
        final ExperimentRepository experimentRepository = (ExperimentRepository) EcoreHelper.loadResourceFromBundle(
                resourceSet, bundle, experimentsLocation, expectedType);

        final List<Experiment> experiments;
        if (filteredExperimentIDs == null || filteredExperimentIDs.length == 0) {
            // experiments as in config
            experiments = experimentRepository.getExperiments();
        } else {
            // filter experiment list
            experiments = new ArrayList<Experiment>();
            for (final Experiment e : experimentRepository.getExperiments()) {
                for (final String id : filteredExperimentIDs) {
                    if (e.getId().equalsIgnoreCase(id)) {
                        experiments.add(e);
                        break;
                    }
                }
            }
        }

        return experiments;
    }
	

}
