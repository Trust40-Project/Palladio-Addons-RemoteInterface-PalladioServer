package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
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
	public edu.kit.palladio.rmi.projectmanagment.IProject createProject(String projectId) throws RemoteException{
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
		return (edu.kit.palladio.rmi.projectmanagment.IProject) new Project(projectId);
	}


	@Override
	public boolean deleteProject(final edu.kit.palladio.rmi.projectmanagment.IProject toDelete) throws RemoteException{
		return deleteProject(toDelete.getProjectId());
	}

	@Override
	public boolean deleteProject(final String projectId) throws RemoteException{
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final org.eclipse.core.resources.IProject projectToDelete = workspaceRoot.getProject(projectId);
		final IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			projectToDelete.delete(org.eclipse.core.resources.IResource.ALWAYS_DELETE_PROJECT_CONTENT | org.eclipse.core.resources.IResource.FORCE, progressMonitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public List<edu.kit.palladio.rmi.projectmanagment.IProject> getProjects() throws RemoteException{
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
		final ArrayList<edu.kit.palladio.rmi.projectmanagment.IProject> result = new ArrayList<edu.kit.palladio.rmi.projectmanagment.IProject>(projects.length);
		edu.kit.palladio.rmi.projectmanagment.IProject toAdd = null;
		for(int i = 0; i < projects.length; i++) {
			toAdd = toProjectManagmentIProject(projects[i]);
			if(toAdd != null) {
				result.add(toAdd);
			}
			
		}
		return result;
	}

	@Override
	public boolean setNatures(edu.kit.palladio.rmi.projectmanagment.IProject projectToSetNatures, String[] natures) throws RemoteException {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
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

	@Override
	public boolean close() {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
		boolean success = true;
		for(org.eclipse.core.resources.IProject projectToSave: projects) {
			try {
				projectToSave.close(new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
			}
		}
		return success;
		
	}

	@Override
	public edu.kit.palladio.rmi.projectmanagment.IProject getProject(final String projectId) throws RemoteException {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final IProject  resultProject = workspaceRoot.getProject(projectId);
		return toProjectManagmentIProject(resultProject);
	}
	
	private edu.kit.palladio.rmi.projectmanagment.IProject toProjectManagmentIProject(final IProject projectToConvert){
		assert(projectToConvert != null);
		if(projectToConvert.exists()) {
			if(!projectToConvert.isOpen()) {
				try {
					projectToConvert.open(new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			try {
				return new edu.kit.palladio.rmi.projectmanagment.Project(projectToConvert.getDescription().getName());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public edu.kit.palladio.rmi.projectmanagment.IProject createProject(
			edu.kit.palladio.rmi.projectmanagment.IProject projectToCreate) throws RemoteException {
		return createProject(projectToCreate.getProjectId());
	}



}
