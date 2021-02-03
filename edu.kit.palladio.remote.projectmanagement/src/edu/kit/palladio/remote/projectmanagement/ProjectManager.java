package edu.kit.palladio.remote.projectmanagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, property = { "id=edu.kit.palladio.remote.projectmanagement", "name=Project Manager"})
public class ProjectManager implements IProjectManager {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient IWorkspace workspace;
	
	public ProjectManager(){
		this.workspace = ResourcesPlugin.getWorkspace();
	}
	

	@Override
	public edu.kit.palladio.remote.projectmanagement.IProject createProject(String projectId) throws Throwable, IllegalStateException{
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final org.eclipse.core.resources.IProject newProject = workspaceRoot.getProject(projectId);
		
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			newProject.create(progressMonitor);
		} catch (CoreException e) {
			throw handleProjectCreationError(newProject);
		}
		try {
			newProject.open(progressMonitor); //TODO: Should all projects always be open?
		} catch (CoreException e) {
			throw new IllegalStateException("resource change not allowed.");
		}
		return new Project(projectId);
	}
	
	private Throwable handleProjectCreationError(final org.eclipse.core.resources.IProject projectToCreate) {
		IStatus status = workspace.validateName(projectToCreate.getName(), IResource.PROJECT);
		if(!status.isOK()) {
			return status.getException();
		} 
		status = workspace.validateProjectLocation(projectToCreate, projectToCreate.getFullPath());
		if(!status.isOK()) {
			return status.getException();
		} 
		return new IllegalArgumentException("the project " + projectToCreate.getName() + " already exists or the project description file could not be created.");
	}


	@Override
	public void deleteProject(final edu.kit.palladio.remote.projectmanagement.IProject toDelete) throws IllegalStateException{
		deleteProject(toDelete.getProjectId());
	}

	@Override
	public void deleteProject(final String projectId) throws IllegalStateException{
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final org.eclipse.core.resources.IProject projectToDelete = workspaceRoot.getProject(projectId);
		final IProgressMonitor progressMonitor = new NullProgressMonitor();
		try {
			projectToDelete.delete(org.eclipse.core.resources.IResource.ALWAYS_DELETE_PROJECT_CONTENT | org.eclipse.core.resources.IResource.FORCE, progressMonitor);
		} catch (CoreException e) {
			throw new IllegalStateException("could not delete project with id " + projectId);
		}
	}
	
	@Override
	public List<edu.kit.palladio.remote.projectmanagement.IProject> getProjects() {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
		final ArrayList<edu.kit.palladio.remote.projectmanagement.IProject> result = new ArrayList<edu.kit.palladio.remote.projectmanagement.IProject>(projects.length);
		edu.kit.palladio.remote.projectmanagement.IProject toAdd = null;
		for(int i = 0; i < projects.length; i++) {
			toAdd = toProjectManagmentIProject(projects[i]);
			if(toAdd != null) {
				result.add(toAdd);
			}
			
		}
		return result;
	}

	@Override
	public void setNatures(edu.kit.palladio.remote.projectmanagement.IProject projectToSetNatures, String[] natures) throws IllegalStateException, IllegalArgumentException {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject projectToSet = workspaceRoot.getProject(projectToSetNatures.getProjectId());
		IProjectDescription projectDescription;
		try {
			projectDescription = projectToSet.getDescription();
		}catch (CoreException e) {
			throw new IllegalArgumentException("the project with id " + projectToSetNatures.getProjectId() + " does not exist or is not open.");
		}
		
		try {
			projectDescription.setNatureIds(natures);
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			projectToSet.setDescription(projectDescription, progressMonitor);
		} catch (CoreException e) {
			throw new IllegalStateException("could not change project description.");
		}
	}

	@Override
	public void close() throws IllegalStateException {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
		LinkedList<String> notSavedProjectIds = new LinkedList<String>();
		for(org.eclipse.core.resources.IProject projectToSave: projects) {
			try {
				projectToSave.close(new NullProgressMonitor());
			} catch (CoreException e) {
				notSavedProjectIds.add(projectToSave.getName());
			}
		}
		if(!notSavedProjectIds.isEmpty()) {
			// some project could not be saved
			throw new IllegalStateException("could not save the following projects: " + notSavedProjectIds.toString());
			
		}
		
	}

	@Override
	public edu.kit.palladio.remote.projectmanagement.IProject getProject(final String projectId) throws IllegalStateException, IllegalArgumentException {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final IProject  resultProject = workspaceRoot.getProject(projectId);
		return toProjectManagmentIProject(resultProject);
	}
	
	private edu.kit.palladio.remote.projectmanagement.IProject toProjectManagmentIProject(final IProject projectToConvert) throws IllegalStateException, IllegalArgumentException{
		assert(projectToConvert != null);
		if(projectToConvert.exists()) {
			if(!projectToConvert.isOpen()) {
				try {
					projectToConvert.open(new NullProgressMonitor());
				} catch (CoreException e) {
					throw new IllegalStateException("could not open project with id " + projectToConvert.getName());
					
				}
			}
			try {
				return new edu.kit.palladio.remote.projectmanagement.Project(projectToConvert.getDescription().getName());
			} catch (CoreException e) {
				throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist or is not open.");
				
			}
		}
		throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist.");
	}

	@Override
	public edu.kit.palladio.remote.projectmanagement.IProject createProject(
			edu.kit.palladio.remote.projectmanagement.IProject projectToCreate) throws IllegalStateException, Throwable {
		return createProject(projectToCreate.getProjectId());
	}



}