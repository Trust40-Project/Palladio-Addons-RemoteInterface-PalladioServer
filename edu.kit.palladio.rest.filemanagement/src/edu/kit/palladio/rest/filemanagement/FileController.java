package edu.kit.palladio.rest.filemanagement;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;

import edu.kit.palladio.remote.filemanagement.EclipseFileInputStream;
import edu.kit.palladio.remote.filemanagement.EclipseFileOutputStream;
import edu.kit.palladio.remote.filemanagement.FileManager;
import edu.kit.palladio.remote.filemanagement.IFileManager;

@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.filemanagement.IFileController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/files" })
public class FileController implements IFileController {

	private static final String PATHPARAMETERURL = "s";
	private IFileManager fileManager;
	
	public FileController() {
		fileManager = new FileManager();
	}
	@Override
	@PUT
    @Path("/project/{projectId}/{s:.*}")
	public void createFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo) throws IOException, IllegalArgumentException {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        fileManager.createFile(path);
	}

	@Override
	@DELETE
    @Path("/project/{projectId}/{s:.*}")
	public void deleteFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo) throws IOException, IllegalArgumentException {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        fileManager.delete(path);
	}

	@Override
	@GET
    @Path("/download/project/{projectId}/{s:.*}")
	public Response downloadFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo) throws IOException, IllegalArgumentException {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
        
	
        try(final InputStream fileToRead =  new EclipseFileInputStream(path)){
        	final CacheControl cacheControl = new CacheControl();
        	cacheControl.setNoCache(true);
        	cacheControl.setNoStore(true);
        	cacheControl.setMustRevalidate(true);
        	return Response
        		.ok(fileToRead, MediaType.APPLICATION_OCTET_STREAM)
        		.cacheControl(cacheControl)
        		.header("Pragma", "no-cache")
        		.header("Expires", "0")
        		.build();
        }
	}

	@Override
	@PUT
    @Path("/upload/project/{projectId}/{s:.*}")
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
	public void newProjectContent(@PathParam("projectId") String projectId, @FormParam("file") InputStream uploadedInputStream, @Context UriInfo uriInfo)
			throws IOException, IllegalArgumentException {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
		try(final OutputStream fileToWriteTo = new EclipseFileOutputStream(path)){
			uploadedInputStream.transferTo(fileToWriteTo);
			uploadedInputStream.close();
		}
		
        
	}

}
