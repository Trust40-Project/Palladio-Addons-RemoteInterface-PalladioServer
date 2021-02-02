package edu.kit.palladio.rest.experimentautomation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edu.kit.palladio.remote.experimentautomation.IExperimentAutomationLaunchConfig;
import edu.kit.palladio.remote.experimentautomation.IExperimentAutomationLauncher;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.experimentautomation.IExperimentAutomationController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/experimentautomation" })
public class ExperimentAutomationController implements IExperimentAutomationController {

	
	 private IExperimentAutomationLauncher launcher;

    @Reference
    public void setExperimentAutomationLauncher(IExperimentAutomationLauncher experimentAutomationLauncher) {
        this.launcher = experimentAutomationLauncher;
    }

    @Override
	@POST
	@Path("/launch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String newLaunch(IExperimentAutomationLaunchConfig launchConfig) throws IllegalStateException, Throwable {
		return this.launcher.launch(launchConfig);
	}
	
}
