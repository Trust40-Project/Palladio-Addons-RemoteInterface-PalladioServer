package edu.kit.palladio.rest.filemanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileUploadException;

import javax.ws.rs.core.Response;  

@Path("")
public interface IFileController {
    @PUT
    @Path("/project/{projectId}/{s:.*}")
    public void createFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo)
            throws IOException, IllegalArgumentException;

  
    @DELETE
    @Path("/project/{projectId}/{s:.*}")
    public void deleteFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo)
            throws IOException, IllegalArgumentException;

  

    
    @GET
    @Path("/download/project/{projectId}/{s:.*}")
    public Response downloadFile(@PathParam("projectId") String projectId, @Context UriInfo uriInfo)
            throws IOException, IllegalArgumentException;


    @POST
    @Path("/upload/project/{projectId}/{s:.*}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public void newProjectContent(@Context HttpServletRequest httpServletRequest, @PathParam("projectId") String projectId,/*InputStream uploadedInputStream,*//*@FormParam("file") InputStream uploadedInputStream,*/ @Context UriInfo uriInfo)
			throws IOException, IllegalArgumentException, FileUploadException;

    
}
