package edu.kit.palladio.proto.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.proto.projectmanagement.CreateProjectResponse;
import edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse;
import edu.kit.palladio.proto.rest.dto.IProject;
import edu.kit.palladio.proto.rest.dto.IProjectManager;
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
    List<String> allProjects() throws RemoteException{
        List<String> serializedProjects = new ArrayList<String>();
        projectManager.getProjects().forEachRemaining(project -> {
            try {
                serializedProjects.add(JsonFormat.printer().print(project));
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        });
        return serializedProjects;
    }

    @Operation(summary = "Creates a new project.")
    @PostMapping("/projects")
    String newProject(@RequestBody edu.kit.palladio.proto.rest.dto.Project newProject) throws InvalidProtocolBufferException {
        CreateProjectResponse response = projectManager.createProject(newProject);
        return JsonFormat.printer().print(response);
    }

    // Single item

    @Operation(summary = "Get a single project.")
    @GetMapping("/projects/{projectId}")
    String oneProject(@Parameter(description = "The id of the project to get.", required = true) @PathVariable String projectId) throws RemoteException, IllegalStateException, IllegalArgumentException,
            InvalidProtocolBufferException {
        edu.kit.palladio.proto.projectmanagement.Project response = projectManager.getProject(projectId);
        return JsonFormat.printer().print(response);
    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @Operation(summary = "Delete a project and all of its data.")
    @DeleteMapping("/projects/{projectId}")
    String deleteProject(@Parameter(description = "The id of the project to delete.", required = true) @PathVariable String projectId) throws RemoteException, IllegalStateException,
            InvalidProtocolBufferException {
       // projectManager.deleteProject(projectId);
        DeleteProjectResponse response = projectManager.deleteProject(projectId);
        return JsonFormat.printer().print(response);
    }
}