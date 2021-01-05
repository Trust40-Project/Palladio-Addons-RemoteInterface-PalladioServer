package edu.kit.palladio.rest.filemanagement;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;

import edu.kit.palladio.remote.filemanagement.FileManager;
import edu.kit.palladio.remote.filemanagement.IFileManager;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.filemanagement.IDirectoryController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/directories" })
public class DirectoryController implements IDirectoryController {

	private static final String PATHPARAMETERURL = "s";
	private IFileManager fileManager;
	
	public DirectoryController() {
		fileManager = new FileManager();
	}
	
	@Override
	@PUT
	@Path("/project/{projectId}/{s:.*}")
	public void createDirectory(String projectId, UriInfo uriInfo) throws IOException, IllegalArgumentException {
		final var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        fileManager.createDirectory(path);
	}

	@Override
	@DELETE
    @Path("/project/{projectId}/{s:.*}")
	public void deleteFile(String projectId, UriInfo uriInfo) throws IOException, IllegalArgumentException {
		final var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        fileManager.delete(path);
	}

}
