package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.RemoteException;

import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ProjectManager implements IProjectManager {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private transient IWorkspace workspace;
	
	public ProjectManager(){
		this.workspace = ResourcesPlugin.getWorkspace();
		System.out.println("workspace root: " + this.workspace.getRoot().getRawLocation());
	}

	@Override
	public IProject createProject(String projectId) throws RemoteException{
		
		System.out.println("create project");
		
		
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject newProject = workspaceRoot.getProject(projectId);
		
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			newProject.create(progressMonitor);
			newProject.open(progressMonitor); //TODO: Should all projects always be open?
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		System.out.println("create project - done");
		return (IProject) new Project(projectId);
	}
	/*
	private boolean doesExist(String projectId) {
		org.eclipse.core.resources.IProject[] projects = workspace.getRoot().getProjects();
		for(org.eclipse.core.resources.IProject project : projects) {
			try {
				if(project.getDescription().getName().equals(projectId)) {
					return true;
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}*/

	@Override
	public boolean deleteProject(IProject toDelete) throws RemoteException{
		System.out.println("delete project");
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject projectToDelete = workspaceRoot.getProject(toDelete.getProjectId());
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			projectToDelete.delete(org.eclipse.core.resources.IResource.ALWAYS_DELETE_PROJECT_CONTENT | org.eclipse.core.resources.IResource.FORCE, progressMonitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		System.out.println("delete project - done");
		return true;
	}

	@Override
	public IProject[] getProjects() throws RemoteException{
		System.out.println("get projects");
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
		IProject[] result = new IProject[projects.length];
		for(int i = 0; i < projects.length; i++) {
			try {
				result[i] = new Project(projects[i].getDescription().getName());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result[i] = null;
			}
		}
		System.out.println("get projects - done");
		return result;
	}

	@Override
	public boolean setNatures(IProject projectToSetNatures, String[] natures) throws RemoteException {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject projectToSet = workspaceRoot.getProject(projectToSetNatures.getProjectId());
		
		
		try {
			IProjectDescription projectDescription = projectToSet.getDescription();
			projectDescription.setNatureIds(natures);
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			projectToSet.setDescription(projectDescription, progressMonitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
