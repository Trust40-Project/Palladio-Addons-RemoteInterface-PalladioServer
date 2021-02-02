package edu.kit.palladio.rest.experimentautomation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.kit.palladio.remote.experimentautomation.IExperimentAutomationLaunchConfig;

@Path("")
public interface IExperimentAutomationController {
	@POST
	@Path("/launch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    String newLaunch(IExperimentAutomationLaunchConfig launchConfig) throws IllegalStateException, Throwable;
}

