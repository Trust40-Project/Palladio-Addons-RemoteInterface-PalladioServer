package edu.kit.palladio.rmi.projectmanagment;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ProjectManager implements IProjectManager {
	
	private IWorkspace workspace;
	
	public ProjectManager(){
		this.workspace = ResourcesPlugin.getWorkspace();
	}

	@Override
	public IProject createProject(String projectId) {
		
		System.out.println("create project");
		// check if project already exists.
		if(doesExist(projectId)) {
			return new Project(projectId);
		}
		
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject newProject = workspaceRoot.getProject(projectId);
		
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			newProject.create(progressMonitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		return new Project(projectId);
	}
	
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
	}

	@Override
	public boolean deleteProject(IProject toDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IProject[] getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
