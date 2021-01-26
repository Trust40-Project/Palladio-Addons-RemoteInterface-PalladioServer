package edu.kit.palladio.rest.filemanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edu.kit.palladio.remote.filemanagement.EclipseFileInputStream;
import edu.kit.palladio.remote.filemanagement.EclipseFileOutputStream;
import edu.kit.palladio.remote.filemanagement.FileManager;
import edu.kit.palladio.remote.filemanagement.IFileManager;

@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.filemanagement.IFileController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/files" })
public class FileController implements IFileController {

	private static final String PATHPARAMETERURL = "s";
	@Reference(service = IFileManager.class)
	private IFileManager fileManager;
	/*@Reference
	private ServletConfig  config;
	*/
	
	public FileController() {
		//this.fileManager = new FileManager();
		System.out.println("---------------------------------------------------------constructor with filemanager called: " + this.fileManager);			
		
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
        
		final InputStream fileToRead =  new EclipseFileInputStream(path);
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

	@Override
	@POST	
    @Path("/upload/project/{projectId}/{s:.*}")
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
	public void newProjectContent(@Context HttpServletRequest httpServletRequest, @PathParam("projectId") String projectId,/*InputStream uploadedInputStream,*//*@FormParam("file") InputStream uploadedInputStream,*/ @Context UriInfo uriInfo)
			throws IOException, IllegalArgumentException, FileUploadException {
		var pathParameters = uriInfo.getPathParameters();
		if(!pathParameters.containsKey(PATHPARAMETERURL)) {
			throw new IllegalArgumentException("request url does not contain a path for a file or directory");
		}
		final String path = projectId + "/" + pathParameters.getFirst(PATHPARAMETERURL);
		
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(httpServletRequest);
		if(!isMultipart) {
			throw new IllegalArgumentException("Expected multipart file upload request.");
		}
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = httpServletRequest.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List<FileItem> items = upload.parseRequest(httpServletRequest);
		
		for(FileItem fileItem: items) {
			if(!fileItem.isFormField()) {
				
				try(final OutputStream fileToWriteTo = new EclipseFileOutputStream(path)){
					try(final InputStream uploadedInputStream = fileItem.getInputStream()){
						uploadedInputStream.transferTo(fileToWriteTo);
					}
				}
			}
			
		}
		/*try(final OutputStream fileToWriteTo = new EclipseFileOutputStream(path)){
			uploadedInputStream.transferTo(fileToWriteTo);
			uploadedInputStream.close();
		}
		*/
        
	}

}
