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

@RestController
public class ProjectsController {
    private IProjectManager projectManager;

    @Autowired
    public ProjectsController(IProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    // Aggregate root
    @GetMapping("/projects")
    List<IProject> allProjects() throws RemoteException {
        return projectManager.getProjects();
       
    }

    @PostMapping("/projects")
    IProject newProject(@RequestBody Project newProject) throws RemoteException {
        return projectManager.createProject(newProject);
    }

    // Single item

    @GetMapping("/projects/{id}")
    IProject oneProject(@PathVariable String projectId) throws RemoteException {
        return projectManager.getProject(projectId);

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @DeleteMapping("/projects/{projectId}")
    void deleteProject(@PathVariable String projectId) throws RemoteException {
        if(!projectManager.deleteProject(projectId)){
            //TODO: return badrequest status code.
        }
    }
}