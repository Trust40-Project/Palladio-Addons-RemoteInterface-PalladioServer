package edu.kit.palladio.rest.resultmanagement;

import edu.kit.palladio.remote.resultmanagement.IResultManager;
import edu.kit.palladio.remote.resultmanagement.ResultManager;

import java.io.Serializable;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.osgi.service.component.annotations.Component;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.projectmanagement.IAnalysisResultController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/solution" })
public class AnalysisResultController implements IAnalysisResultController {

	private IResultManager resultManager;
	
	public AnalysisResultController() {
		resultManager = new ResultManager();
	}
	
	@Override
	@GET
	@Path("/{launchId}")
	public Serializable oneSolution(@PathParam("launchId") String launchId)
			throws IllegalStateException, IllegalArgumentException {
		return resultManager.getSolution(launchId);
	}

}
