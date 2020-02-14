package edu.kit.palladio.rmi.supportingprolog4j;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.prolog4j.IProverFactory;
import org.prolog4j.ProverInformation;
import org.prolog4j.manager.IProverManager;

import edu.kit.palladio.rcp.api.ILoadMe;

@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI",
		"name=Prover Manager" })
public class ProverManageWrapper implements ILoadMe, IProverManagerRMI {

	@Reference(service = IProverManager.class)
	private IProverManager proverManagerInstance;
	// private final IProverManager proverManagerInstance;
	private static final String RMIID = "edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI";

	/*
	 * ProverManageWrapper(){ ServiceReference<IProverManager>
	 * proverManagerReference = Activator.getContext()
	 * .getServiceReference(IProverManager.class); this.proverManagerInstance =
	 * Activator.getContext().getService(proverManagerReference); }
	 */

	@Override
	public String getRmiId() throws RemoteException {
		return RMIID;
	}

	@Override
	public Collection<ProverInformation> getProvers() throws RemoteException {
		return this.proverManagerInstance.getProvers().keySet();
	}

}
