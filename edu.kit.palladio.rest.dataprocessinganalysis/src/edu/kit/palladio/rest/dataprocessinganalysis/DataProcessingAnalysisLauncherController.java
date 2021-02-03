package edu.kit.palladio.rest.dataprocessinganalysis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edu.kit.palladio.remote.dataprocessinganalysis.IAnalysisLauncher;
import edu.kit.palladio.remote.dataprocessinganalysis.LaunchConfig;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.dataprocessinganalysis.IDataProcessingAnalysisLauncherController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/dataprocessinganalysis" })
public class DataProcessingAnalysisLauncherController implements IDataProcessingAnalysisLauncherController {

	@Reference(service = IAnalysisLauncher.class)
	private IAnalysisLauncher analysisLauncher;
	
	
	@Override
	@POST
	@Path("/launch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String newLaunch(LaunchConfig launchConfig) throws IllegalStateException, Throwable {
		return analysisLauncher.launch(launchConfig);
	}

}
