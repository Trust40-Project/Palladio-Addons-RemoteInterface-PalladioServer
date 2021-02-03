package edu.kit.palladio.rest.resultmanagement;

import edu.kit.palladio.remote.resultmanagement.IResultManager;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.projectmanagement.IAnalysisResultController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/solution" })
public class AnalysisResultController implements IAnalysisResultController {

	private final static int MAXNUMMILLISECTOWAIT = 100;
	private IResultManager resultManager;
	
	@Reference
	public void setResultManager(IResultManager resultManager) {
		this.resultManager = resultManager;
	}
	
	@Override
	@GET
	@Path("/{launchId}")
	public Serializable oneSolution(@PathParam("launchId") String launchId)
			throws IllegalStateException, IllegalArgumentException {
				final Future<Serializable> futureSolution = resultManager.getSolution(launchId);
				if (futureSolution == null) {
					throw new IllegalStateException("No solution for the launch id " + launchId + " could be found.");
				}
				if (futureSolution.isCancelled()) {
					throw new IllegalStateException("The launch was canceled. Please try launching it again.");
				}
				if (!futureSolution.isDone()) {
					throw new IllegalStateException("The launch is not yet done.");
				}
				try {
					return futureSolution.get(MAXNUMMILLISECTOWAIT, TimeUnit.MILLISECONDS);

				} catch (InterruptedException e) {
					throw new IllegalStateException("Could not get solution for launch. Please try again.");
				} catch (ExecutionException e) {
					throw new IllegalStateException("Launch could not get execute because of " + e.getLocalizedMessage()
							+ " Please fix the problem and try launching again.");
				} catch (TimeoutException e) {
					throw new IllegalStateException("Could not get solution for launch. Please try again.");
				}
	}

}
