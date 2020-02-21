package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rcp.api.ISolutionManagerRemote;
import edu.kit.palladio.rmi.projectmanagment.IProject;

@RestController
public class SolutionController {
    private ISolutionManagerRemote solutionManager;

    @Autowired
    public SolutionController(ISolutionManagerRemote solutionManager) {
        this.solutionManager = solutionManager;
    }

    // Aggregate root
    @GetMapping("/solution")
    List<IProject> allSolutions() throws RemoteException {
        //TODO
        return null;
       
    }

    /*
    @PostMapping("/solution")
    String newSolution() throws RemoteException, IllegalStateException, Throwable {
    }*/

    // Single item

    @GetMapping("/solution/{launchId}")
    IProject oneSolution(@PathVariable String launchId) throws RemoteException, IllegalStateException, IllegalArgumentException {
        //TODO
        this.solutionManager.getSolution(launchId);
        return null;

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @DeleteMapping("/solution/{launchId}")
    void deleteSolution(@PathVariable String launchId) throws RemoteException, IllegalStateException {
        //TODO
        
    }
}