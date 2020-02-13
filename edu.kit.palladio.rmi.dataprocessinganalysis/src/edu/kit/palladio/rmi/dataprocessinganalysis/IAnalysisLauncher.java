package edu.kit.palladio.rmi.dataprocessinganalysis;


import java.rmi.Remote;
import java.rmi.RemoteException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

public interface IAnalysisLauncher extends Remote {
	void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws RemoteException, IllegalArgumentException;
}
