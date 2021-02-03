package edu.kit.palladio.rest.filemanagement;

import edu.kit.palladio.remote.filemanagement.File;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("")
public interface IProjectController {
	
	@GET
	@Path("/{projectId}/{s:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<File> listFilesAndDiretories(@PathParam("projectId") String projectId, @Context UriInfo uriInfo);
}
