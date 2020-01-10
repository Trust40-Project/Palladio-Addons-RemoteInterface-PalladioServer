package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.Remote;

public interface IProjectManager extends Remote {
	IProject createProject(String projectId);
	boolean deleteProject(IProject toDelete);
	IProject[] getProjects();
}
