package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.Collection;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI;

@RestController
public class QueryInformationController {
    private IQueryManagerRMI queryManager;

    @Autowired
    public QueryInformationController(IQueryManagerRMI queryManager) {
        this.queryManager = queryManager;
    }

    // Aggregate root
    @GetMapping("/queryinformation")
    Collection<QueryInformation> allProvers() throws RemoteException {
        return queryManager.getQueries();
    }

    // Single item

    @GetMapping("/queryinformation/{id}")
    QueryInformation oneProver(@PathVariable String queryId) throws RemoteException, IllegalArgumentException {
        for (QueryInformation queryInformation : this.queryManager.getQueries()) {
            if(queryInformation.getId().equals(queryId)){
                return queryInformation;
            }
        }
        throw new IllegalArgumentException("No available query information with id: " + queryId + " found.");

    }
}