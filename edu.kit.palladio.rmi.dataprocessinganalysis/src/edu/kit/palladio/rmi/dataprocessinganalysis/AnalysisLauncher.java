package edu.kit.palladio.rmi.dataprocessinganalysis;

import java.rmi.RemoteException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.IQuery;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflow;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.workflow.AnalysisWorkflowConfig;
import org.prolog4j.IProverFactory;
import org.prolog4j.ProverInformation;
import org.prolog4j.manager.IProverManager;

import edu.kit.palladio.rcp.api.ILoadMe;

@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.dataprocessinganalysis.analysislauncher", "name=Analysis Launcher"})
public class AnalysisLauncher implements IAnalysisLauncher, ILoadMe {

	private final static String RMIID = "du.kit.palladio.rmi.dataprocessinganalysis.IAnalysisLauncher";
	private final transient IWorkspace workspace = ResourcesPlugin.getWorkspace();

	@Reference(service = IProverManager.class)
	private IProverManager proverManager;

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws IllegalArgumentException, RemoteException {
		
		AnalysisWorkflowConfig analysisWorkflowConfig;
		try {
			analysisWorkflowConfig = new AnalysisWorkflowConfig(
					this.getUsageModelPath(configuration),
					this.getAllocModelPath(configuration),
					this.getcharacterisitcsModelPath(configuration),
					this.getAnalysisGoal(configuration),
					this.getProverFactory(configuration),
					this.getReturnValueIndexing(configuration),
					this.getOptimNegation(configuration),
					this.getShortAssign(configuration)
					);
		}  catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("Could not use provided launch configuration.");
		}
		
		 AnalysisWorkflow analysisWorkflow = new AnalysisWorkflow(analysisWorkflowConfig);
		 
		 analysisWorkflow.launch();

	}

	private IProverFactory getProverFactory(ILaunchConfiguration launchConfig) throws CoreException {

		final String prologConfig = launchConfig.getAttribute(Constants.PROLOG_INTERPRETER_LABEL.getConstant(),
				"default");
		for (Map.Entry<ProverInformation, IProverFactory> entry : proverManager.getProvers().entrySet()) {
			if (entry.getKey().getId().equals(prologConfig)) {
				return entry.getValue();
			}
		}
		return null;

	}

	private IQuery getAnalysisGoal(ILaunchConfiguration launchConfig) throws CoreException {
		String analysisConfig = launchConfig.getAttribute(Constants.ANALYSIS_GOAL_LABEL.getConstant(), "default");

		if (!analysisConfig.equals("default")) {
			for (Map.Entry<QueryInformation, IQuery> entry : Activator.getInstance().getQueryManager().getQueries().entrySet()) {
				if (entry.getKey().getId().equals(analysisConfig)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	private URI getUsageModelPath(ILaunchConfiguration configuration) throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getAttribute(Constants.USAGE_MODEL_LABEL.getConstant(), ""));
	}

	private URI getAllocModelPath(ILaunchConfiguration configuration) throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getAttribute(Constants.ALLOCATION_MODEL_LABEL.getConstant(), ""));
	}

	private URI getcharacterisitcsModelPath(ILaunchConfiguration configuration)
			throws IllegalArgumentException, CoreException {
		return getUriFromText(configuration.getAttribute(Constants.CHARACTERISTICS_MODEL_LABEL.getConstant(), ""));
	}

	private boolean getReturnValueIndexing(ILaunchConfiguration configuration) throws CoreException {
		return configuration.getAttribute(Constants.ADV_ARG_AND_RETURN.getConstant(), false);
	}

	private boolean getOptimNegation(ILaunchConfiguration configuration) throws CoreException {
		return configuration.getAttribute(Constants.ADV_OPTIM_NEGATION.getConstant(), false);
	}

	private boolean getShortAssign(ILaunchConfiguration configuration) throws CoreException {
		return configuration.getAttribute(Constants.ADV_SHORT_ASSIGN.getConstant(), false);
	}

	private URI getUriFromText(String pathText) throws CoreException, IllegalArgumentException {
		IPath path = new org.eclipse.core.runtime.Path(pathText);
		if (!path.isValidPath(pathText)) {
			throw new IllegalArgumentException("Invalid model path.");
		}

		IWorkspaceRoot workspaceRoot = workspace.getRoot();

		// Does the path point to a file?
		IFile file = workspaceRoot.getFile(path);
		URI result;
		if (file.exists()) {
			result = URI.createFileURI(file.getFullPath().toString());
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
