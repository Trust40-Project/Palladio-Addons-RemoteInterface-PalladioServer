package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.projectmanagment.IProject;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;
import edu.kit.palladio.rmi.projectmanagment.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ProjectsController {
    private IProjectManager projectManager;

    @Autowired
    public ProjectsController(IProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    // Aggregate root
    @Operation(summary = "Returns all projects.")
    @GetMapping("/projects")
    List<IProject> allProjects() throws RemoteException {
        return projectManager.getProjects();
       
    }

    @Operation(summary = "Creates a new project.")
    @PostMapping("/projects")
    IProject newProject(@RequestBody Project newProject) throws RemoteException, IllegalStateException, Throwable {
        return projectManager.createProject(newProject);
    }

    // Single item

    @Operation(summary = "Get a single project.")
    @GetMapping("/projects/{id}")
    IProject oneProject(@Parameter(description = "The id of the project to get.", required = true) @PathVariable String projectId) throws RemoteException, IllegalStateException, IllegalArgumentException {
        return projectManager.getProject(projectId);

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @Operation(summary = "Delete a project and all of its data.")
    @DeleteMapping("/projects/{projectId}")
    void deleteProject(@Parameter(description = "The id of the project to delete.", required = true) @PathVariable String projectId) throws RemoteException, IllegalStateException {
        projectManager.deleteProject(projectId);
    }
}