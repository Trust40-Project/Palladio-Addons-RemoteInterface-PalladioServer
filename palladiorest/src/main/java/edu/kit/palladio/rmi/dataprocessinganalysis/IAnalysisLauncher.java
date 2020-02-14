package edu.kit.palladio.rmi.dataprocessinganalysis;


import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IAnalysisLauncher extends Remote {
	void launch(final LaunchConfig launchConfig) throws RemoteException, IllegalArgumentException;
}
