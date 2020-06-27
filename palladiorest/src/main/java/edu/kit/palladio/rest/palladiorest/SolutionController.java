package edu.kit.palladio.rest.palladiorest;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rcp.api.ISolutionManagerRemote;
import edu.kit.palladio.rmi.projectmanagment.IProject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

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
    @Operation(summary = "Returns the result to a certain analysis launch.")
    @GetMapping("/solution/{launchId}")
    Map<String, Serializable> oneSolution(@Parameter(description = "The id of the analysis as returned by the launch endpoint when starting an analysis.", required = true) @PathVariable String launchId) throws RemoteException, IllegalStateException, IllegalArgumentException {
        //TODO
        return this.solutionManager.getSolution(launchId);

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