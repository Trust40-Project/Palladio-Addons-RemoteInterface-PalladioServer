package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI;
import edu.kit.palladio.rmi.querymanagement.QueryInformationSerializable;

@RestController
public class QueryInformationController {
    private IQueryManagerRMI queryManager;

    @Autowired
    public QueryInformationController(IQueryManagerRMI queryManager) {
        this.queryManager = queryManager;
    }

    // Aggregate root
    @GetMapping("/queryinformation")
    List<QueryInformationSerializable> allProvers() throws RemoteException {
        return queryManager.getQueries();
    }

    // Single item

    @GetMapping("/queryinformation/{id}")
    QueryInformationSerializable oneProver(@PathVariable String queryId) throws RemoteException, IllegalArgumentException {
        for (QueryInformationSerializable queryInformation : this.queryManager.getQueries()) {
            if(queryInformation.getId().equals(queryId)){
                return queryInformation;
            }
        }
        throw new IllegalArgumentException("No available query information with id: " + queryId + " found.");

    }
}