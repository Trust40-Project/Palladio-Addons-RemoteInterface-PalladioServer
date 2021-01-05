package edu.kit.palladio.rest.filemanagement;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("")
public interface IDirectoryController {
	@PUT
	@Path("/project/{projectId}/{s:.*}")
    public void createDirectory(@PathParam("projectId") String projectId, @Context UriInfo uriInfo)
            throws IOException, IllegalArgumentException;

   
    @DELETE
    @Path("/project/{projectId}/{s:.*}")
    public void deleteFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo)
            throws IOException, IllegalArgumentException;
}
