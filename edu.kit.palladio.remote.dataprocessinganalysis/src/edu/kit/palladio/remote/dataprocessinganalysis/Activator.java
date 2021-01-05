package edu.kit.palladio.remote.dataprocessinganalysis;


import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.impl.IQueryManager;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Activator instance;
	private volatile IQueryManager queryManager;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Activator.instance = this;
		/*
		 * ServiceReference<IQueryManager> queryManagerReference =
		 * context.getServiceReference(IQueryManager.class); this.queryManager =
		 * context.getService(queryManagerReference);
		 */
		IProfileRegistry.eINSTANCE.getClass();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		Activator.instance = null;
	}

	public static Activator getInstance() {
		return instance;
	}
/*
	public IQueryManager getQueryManager() {
		return queryManager;
	}*/

	public IQueryManager getQueryManager() {
		IQueryManager foundQueryManager = queryManager;
		if (foundQueryManager == null) {
			synchronized (this) {
				foundQueryManager = queryManager;
				if (foundQueryManager == null) {
					BundleContext bundleContext = Activator.getContext();
					ServiceReference<IQueryManager> queryManagerReference = bundleContext
							.getServiceReference(IQueryManager.class);
					foundQueryManager = bundleContext.getService(queryManagerReference);
					queryManager = foundQueryManager;
				}
			}
		}
		return foundQueryManager;

	}
}
