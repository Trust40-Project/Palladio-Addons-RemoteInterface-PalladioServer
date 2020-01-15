package edu.kit.palladio.rmi.projectmanagment;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProject extends Remote, Serializable {
	String getProjectId() throws RemoteException;
	
}
