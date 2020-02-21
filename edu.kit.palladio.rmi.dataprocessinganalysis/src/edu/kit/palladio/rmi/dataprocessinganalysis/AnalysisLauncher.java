package edu.kit.palladio.rmi.dataprocessinganalysis;

import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.IQuery;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflow;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflowConfig;
import org.prolog4j.IProverFactory;
import org.prolog4j.ProverInformation;
import org.prolog4j.Solution;
import org.prolog4j.manager.IProverManager;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import edu.kit.palladio.rcp.api.ILoadMe;
import edu.kit.palladio.rcp.api.ISolutionManager;

@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.dataprocessinganalysis.analysislauncher", "name=Analysis Launcher"})
public class AnalysisLauncher implements IAnalysisLauncher, ILoadMe {

	private final static String RMIID = "edu.kit.palladio.rmi.dataprocessinganalysis.IAnalysisLauncher";
	private final transient IWorkspace workspace = ResourcesPlugin.getWorkspace();
	private static ExecutorService executor;
	
	@Reference(service = IProverManager.class)
	private IProverManager proverManager;
	
	@Reference(service = ISolutionManager.class)
	private ISolutionManager solutionManager;
	
	public AnalysisLauncher(){
		ExecutorService currentExecutor = AnalysisLauncher.executor;
		if (currentExecutor == null) {
			synchronized (AnalysisLauncher.class) {
				currentExecutor = AnalysisLauncher.executor;
				if (currentExecutor == null) {
					AnalysisLauncher.executor = Executors.newCachedThreadPool();
				}
			}
		}
	}

	

	@Override
	public String launch(ILaunchConfig launchConfig) throws IllegalArgumentException, RemoteException {
		boolean returnValueIndexing = false;
		boolean optimNegation = false;
		boolean shortAssign = false;
		for(LaunchFlags flag : Optional.ofNullable(launchConfig.getLaunchFlags()).orElseGet(() -> new LaunchFlags[0])) {
			switch(flag) {
				case RETURNVALUEINDEXING:
					returnValueIndexing = true;
					break;
				case OPTIMNEGATION:
					optimNegation = true;
					break;
				case SHORTASSIGN:
					shortAssign = true;
					break;
				default:
					break;
			}
		}
		
		AnalysisWorkflowConfig analysisWorkflowConfig;
		try {
			analysisWorkflowConfig = new AnalysisWorkflowConfig(
					this.getUsageModelPath(launchConfig),
					this.getAllocModelPath(launchConfig),
					this.getCharacteristicsModelPath(launchConfig),
					this.getAnalysisGoal(launchConfig),
					this.getProverFactory(launchConfig),
					returnValueIndexing,
					optimNegation,
					shortAssign
					);
		}  catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("Could not use provided launch configuration.");
		}
		
		 AnalysisWorkflow analysisWorkflow = new AnalysisWorkflow(analysisWorkflowConfig);
		 analysisWorkflow.getBlackboard();
		 
		 Future<Solution<Object>> futureSolution = executor.submit(() -> {
				try {
					analysisWorkflow.execute(new NullProgressMonitor());
					return analysisWorkflow.getBlackboard().getSolution();
				} catch (JobFailedException | UserCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			});
		 Instant now = Instant.now();
		 final String launchId = launchConfig.getLaunchName()+ "-" + now.toString();
		 solutionManager.registerFutureSolution(launchId, futureSolution);
		 
		 return launchId;
	}

	private IProverFactory getProverFactory(ILaunchConfig launchConfig) throws CoreException {

		final String proverFactoryId = launchConfig.getProverFactoryId();
		for (Map.Entry<ProverInformation, IProverFactory> entry : proverManager.getProvers().entrySet()) {
			if (entry.getKey().getId().equals(proverFactoryId)) {
				return entry.getValue();
			}
		}
		return null;

	}

	private IQuery getAnalysisGoal(ILaunchConfig launchConfig) throws CoreException {
		String analysisGoalId = launchConfig.getAnalysisGoalId();

		if (!analysisGoalId.equals("default")) {
			for (Map.Entry<QueryInformation, IQuery> entry : Activator.getInstance().getQueryManager().getQueries().entrySet()) {
				if (entry.getKey().getId().equals(analysisGoalId)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	private URI getUsageModelPath(ILaunchConfig configuration) throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getUsageModelPath());
	}

	private URI getAllocModelPath(ILaunchConfig configuration) throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getAllocModelPath());
	}

	private URI getCharacteristicsModelPath(ILaunchConfig configuration)
			throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getCharacteristicsModelPath());
	}

	

	private URI getUriFromText(String pathText) throws CoreException, IllegalArgumentException {
		
		//TODO: Use https://download.eclipse.org/modeling/emf/emf/javadoc/2.4.3/org/eclipse/emf/common/util/URI.html#createPlatformResourceURI(java.lang.String,%20boolean)
		IPath path = new org.eclipse.core.runtime.Path(pathText);
		if (!path.isValidPath(pathText)) {
			throw new IllegalArgumentException("Invalid model path.");
		}

		IWorkspaceRoot workspaceRoot = workspace.getRoot();

		// Does the path point to a file?
		IFile file = workspaceRoot.getFile(path);
		URI result;
		if (file.exists()) {
			result = URI.createPlatformResourceURI(pathText, false);
		} else {
			result = URI.createFileURI(pathText);
		}

		return result;
	}

	@Override
	public String getRmiId() throws RemoteException {
		return RMIID;
	}

}
