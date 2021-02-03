package edu.kit.palladio.rest.projectmanagement;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.kit.palladio.remote.projectmanagement.IProject;
import edu.kit.palladio.remote.projectmanagement.Project;
import javax.ws.rs.core.MediaType;

@Path("")
public interface IProjectsController {
	
		
	 // Aggregate root
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<IProject> allProjects();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    IProject newProject(Project newProject) throws IllegalStateException, Throwable;

    // Single item
    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    IProject oneProject(@PathParam("projectId") String projectId) throws IllegalStateException, IllegalArgumentException;

    @DELETE
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteProject(@PathParam("projectId") String projectId) throws  IllegalStateException;
}
