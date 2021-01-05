package edu.kit.palladio.rest.dataprocessinganalysis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;

import edu.kit.palladio.remote.dataprocessinganalysis.AnalysisLauncher;
import edu.kit.palladio.remote.dataprocessinganalysis.IAnalysisLauncher;
import edu.kit.palladio.remote.dataprocessinganalysis.LaunchConfig;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.filemanagement.IDataProcessingAnalysisLauncherController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/dataprocessinganalysis" })
public class DataProcessingAnalysisLauncherController implements IDataProcessingAnalysisLauncherController {

	private IAnalysisLauncher analysisLauncher;
	
	public DataProcessingAnalysisLauncherController() {
		analysisLauncher = new AnalysisLauncher();
	}
	
	@Override
	@POST
	@Path("/launch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String newLaunch(LaunchConfig launchConfig) throws IllegalStateException, Throwable {
		return analysisLauncher.launch(launchConfig);
	}

}
