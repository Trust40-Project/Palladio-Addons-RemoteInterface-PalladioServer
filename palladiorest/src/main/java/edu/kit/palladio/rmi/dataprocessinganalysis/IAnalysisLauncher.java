package edu.kit.palladio.rmi.dataprocessinganalysis;


import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IAnalysisLauncher extends Remote {
	String launch(final ILaunchConfig launchConfig) throws RemoteException, IllegalArgumentException;
}
