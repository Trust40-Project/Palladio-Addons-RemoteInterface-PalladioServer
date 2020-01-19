package edu.kit.palladio.rmi.projectmanagment;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProjectManager extends Remote, Serializable {
	IProject createProject(String projectId) throws RemoteException;
	boolean deleteProject(IProject toDelete) throws RemoteException;
	IProject[] getProjects() throws RemoteException;
	boolean setNatures(IProject projectToSetNatures, String[] natures) throws RemoteException;
	boolean close() throws RemoteException;
}
