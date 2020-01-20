package edu.kit.palladio.rmi.projectmanagment;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProjectManager extends Remote, Serializable {
	//post complete project with description and natures
	IProject createProject(final IProject projectToCreate) throws RemoteException;
	IProject createProject(final String projectId) throws RemoteException;
	boolean deleteProject(final String projectId) throws RemoteException;
	boolean deleteProject(final IProject toDelete) throws RemoteException;
	IProject getProject(final String projectId) throws RemoteException;
	List<IProject> getProjects() throws RemoteException;
	boolean setNatures(final IProject projectToSetNatures, final String[] natures) throws RemoteException;
	boolean close() throws RemoteException;
}
