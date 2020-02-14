package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI;
import edu.kit.palladio.rmi.supportingprolog4j.ProverInformationSerializable;

@RestController
public class PrologProverController {
    private IProverManagerRMI proverManager;

    @Autowired
    public PrologProverController(IProverManagerRMI proverManager) {
        this.proverManager = proverManager;
    }

    // Aggregate root
    @GetMapping("/provers")
    List<ProverInformationSerializable> allProvers() throws RemoteException {
        return proverManager.getProvers();
    }

    // Single item
    @GetMapping("/provers/{id}")
    ProverInformationSerializable oneProver(@PathVariable String proverId) throws RemoteException, IllegalArgumentException {
        for (ProverInformationSerializable proverInformation : this.proverManager.getProvers()) {
            if(proverInformation.getId().equals(proverId)){
                return proverInformation;
            }
        }
        throw new IllegalArgumentException("No available prolog prover with id: " + proverId + " found.");

    }
}