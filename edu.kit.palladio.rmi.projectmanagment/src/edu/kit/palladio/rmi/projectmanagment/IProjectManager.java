package edu.kit.palladio.rmi.projectmanagment;

public interface IProjectManager {
	IProject createProject(String projectId);
	boolean deleteProject(IProject toDelete);
	IProject[] getProjects();
}
