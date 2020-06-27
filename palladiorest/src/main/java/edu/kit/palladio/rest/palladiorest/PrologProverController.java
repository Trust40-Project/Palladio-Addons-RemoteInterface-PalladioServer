package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI;
import edu.kit.palladio.rmi.supportingprolog4j.ProverInformationSerializable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class PrologProverController {
    private IProverManagerRMI proverManager;

    @Autowired
    public PrologProverController(IProverManagerRMI proverManager) {
        this.proverManager = proverManager;
    }

    // Aggregate root
    @Operation(summary = "Returns all installed provers.")
    @GetMapping("/provers")
    List<ProverInformationSerializable> allProvers() throws RemoteException {
        return proverManager.getProvers();
    }

    // Single item
    @Operation(summary = "Returns the prover information for a certain installed prover.")
    @GetMapping("/provers/{id}")
    ProverInformationSerializable oneProver(@Parameter(description = "The id of the prover to get information about.", required = true) @PathVariable String proverId) throws RemoteException, IllegalArgumentException {
        for (ProverInformationSerializable proverInformation : this.proverManager.getProvers()) {
            if(proverInformation.getId().equals(proverId)){
                return proverInformation;
            }
        }
        throw new IllegalArgumentException("No available prolog prover with id: " + proverId + " found.");

    }
}