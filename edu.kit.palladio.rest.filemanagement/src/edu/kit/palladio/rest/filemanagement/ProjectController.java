package edu.kit.palladio.rest.filemanagement;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edu.kit.palladio.remote.filemanagement.File;
import edu.kit.palladio.remote.filemanagement.IFileManager;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.filemanagement.IProjectController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/ls/project" })
public class ProjectController implements IProjectController {
	
	private IFileManager fileManager;
	private static final String PATHPARAMETERURL = "s";
	
	
	@Reference
	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	@Override
	@GET
	@Path("/{projectId}/{s:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<File> listFilesAndDiretories(@PathParam("projectId") String projectId, @Context UriInfo uriInfo) {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        return this.fileManager.listFilesAndDirectories(path);
	}

}
