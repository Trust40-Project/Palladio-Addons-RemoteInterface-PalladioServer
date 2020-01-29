package edu.kit.palladio.rmi.projectmanagment;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProjectManager extends Remote, Serializable {
	//post complete project with description and natures
	IProject createProject(final IProject projectToCreate) throws RemoteException, Throwable, IllegalStateException;
	IProject createProject(final String projectId) throws RemoteException, Throwable, IllegalStateException;
	void deleteProject(final String projectId) throws RemoteException, IllegalStateException;
	void deleteProject(final IProject toDelete) throws RemoteException, IllegalStateException;
	IProject getProject(final String projectId) throws RemoteException, IllegalStateException, IllegalArgumentException;
	List<IProject> getProjects() throws RemoteException;
	void setNatures(final IProject projectToSetNatures, final String[] natures) throws RemoteException,IllegalStateException, IllegalArgumentException;
	void close() throws RemoteException, IllegalStateException;
}
