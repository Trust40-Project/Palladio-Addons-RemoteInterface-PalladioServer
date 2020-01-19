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

@RestController
public class ProjectController {
    private IProjectManager projectManager;

    @Autowired
    public ProjectController(IProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    // Aggregate root
    @GetMapping("/projects")
    List<IProject> allProjects() {
        return null;
    }

    @PostMapping("/projects")
    IProject newProject(@RequestBody IProject newProject) {
        return null;
    }

    // Single item

    @GetMapping("/projects/{id}")
    IProject oneProject(@PathVariable String projectId) {
        return null;

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @DeleteMapping("/projects/{projectId}")
    void deleteProject(@PathVariable String projectId) {
    }
}