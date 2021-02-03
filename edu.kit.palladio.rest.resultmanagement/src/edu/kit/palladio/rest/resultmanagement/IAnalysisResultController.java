package edu.kit.palladio.rest.resultmanagement;

import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("")
public interface IAnalysisResultController {
	@GET
	@Path("/{launchId}")
    Serializable oneSolution(@PathParam("launchId") String launchId) throws IllegalStateException, IllegalArgumentException;
      
}
