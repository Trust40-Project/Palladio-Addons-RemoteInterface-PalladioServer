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
import edu.kit.palladio.remote.projectmanagement.IProjectManager;
import edu.kit.palladio.remote.projectmanagement.Project;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Path("")
@Component(immediate = true, property = { "service.exported.interfaces=edu.kit.palladio.rest.projectmanagement.IProjectsController", "service.exported.intents=osgi.async",
		"service.exported.intents=jaxrs", "osgi.basic.timeout=5000000", "ecf.jaxrs.server.pathPrefix=/projects" })
public class ProjectsController implements IProjectsController {
	
	private IProjectManager projectManager;
	
	@Reference
	public void setProjectManager(IProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	
	 // Aggregate root
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public
    List<IProject> allProjects() {
        return projectManager.getProjects();
       
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public
    IProject newProject(Project newProject) throws IllegalStateException, Throwable {
        return projectManager.createProject(newProject);
    }

    // Single item
    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
	public
    IProject oneProject(@PathParam("projectId") String projectId) throws IllegalStateException, IllegalArgumentException {
        return projectManager.getProject(projectId);

    }

    @DELETE
    @Path("/{projectId}")
	public
    void deleteProject(@PathParam("projectId") String projectId) throws  IllegalStateException {
        projectManager.deleteProject(projectId);
    }
}
