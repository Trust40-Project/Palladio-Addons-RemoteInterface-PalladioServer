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

import edu.kit.palladio.rmi.dataprocessinganalysis.IAnalysisLauncher;
import edu.kit.palladio.rmi.dataprocessinganalysis.ILaunchConfig;
import edu.kit.palladio.rmi.projectmanagment.IProject;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;
import edu.kit.palladio.rmi.projectmanagment.Project;

@RestController
public class LaunchController {
    //TODO
    private IAnalysisLauncher analysisLauncher;

    @Autowired
    public LaunchController(IAnalysisLauncher analysisLauncher) {
        this.analysisLauncher = analysisLauncher;
    }

    // Aggregate root
    @GetMapping("/launch")
    List<IProject> allProjects() throws RemoteException {
        //TODO
        return null;
       
    }

    @PostMapping("/launch")
    void newProject(@RequestBody ILaunchConfig launchConfig) throws RemoteException, IllegalStateException, Throwable {
        this.analysisLauncher.launch(launchConfig);;
    }

    // Single item

    @GetMapping("/launch/{id}")
    IProject oneProject(@PathVariable String projectId) throws RemoteException, IllegalStateException, IllegalArgumentException {
        //TODO
        return null;

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @DeleteMapping("/launch/{projectId}")
    void deleteProject(@PathVariable String projectId) throws RemoteException, IllegalStateException {
        //TODO
        
    }
}