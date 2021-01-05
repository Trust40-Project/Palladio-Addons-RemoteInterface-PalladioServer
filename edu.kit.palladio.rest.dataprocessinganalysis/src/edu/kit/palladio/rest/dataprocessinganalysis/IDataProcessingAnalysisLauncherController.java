package edu.kit.palladio.rest.dataprocessinganalysis;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.kit.palladio.remote.dataprocessinganalysis.LaunchConfig;

@Path("")
public interface IDataProcessingAnalysisLauncherController {
	@POST
	@Path("/launch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    String newLaunch(LaunchConfig launchConfig) throws IllegalStateException, Throwable;
}
