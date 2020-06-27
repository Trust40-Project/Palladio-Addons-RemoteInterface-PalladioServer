package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI;
import edu.kit.palladio.rmi.querymanagement.QueryInformationSerializable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class QueryInformationController {
    private IQueryManagerRMI queryManager;

    @Autowired
    public QueryInformationController(IQueryManagerRMI queryManager) {
        this.queryManager = queryManager;
    }

    // Aggregate root
    @Operation(summary = "Returns all the available query information.")
    @GetMapping("/queryinformation")
    List<QueryInformationSerializable> allQueries() throws RemoteException {
        return queryManager.getQueries();
    }

    // Single item
    @Operation(summary = "Returns information to one query.")
    @GetMapping("/queryinformation/{id}")
    QueryInformationSerializable oneQuery(@Parameter(description = "The id of the query to get information about.", required = true) @PathVariable String queryId) throws RemoteException, IllegalArgumentException {
        for (QueryInformationSerializable queryInformation : this.queryManager.getQueries()) {
            if(queryInformation.getId().equals(queryId)){
                return queryInformation;
            }
        }
        throw new IllegalArgumentException("No available query information with id: " + queryId + " found.");

    }
}