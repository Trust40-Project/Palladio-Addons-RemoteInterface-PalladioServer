package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.RemoteException;
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

import edu.kit.palladio.rcpapi.ILoadMe;



@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.projectmanagment.projectmanager", "name=Project Manager"/*, "needsNativeExecutables=false" */})
public class ProjectManager implements IProjectManager, ILoadMe {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String RMIID = "edu.kit.palladio.rmi.projectmanagment.IProjectManager";
	
	
	private transient IWorkspace workspace;
	
	public ProjectManager(){
		this.workspace = ResourcesPlugin.getWorkspace();
		System.out.println("workspace root: " + this.workspace.getRoot().getRawLocation());
	}
	
	@Override
	public String getRmiId() throws RemoteException {
		return RMIID;
	}

	@Override
	public edu.kit.palladio.rmi.projectmanagment.IProject createProject(String projectId) throws Throwable, IllegalStateException{
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
		
		return (edu.kit.palladio.rmi.projectmanagment.IProject) new Project(projectId);
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
	public void deleteProject(final edu.kit.palladio.rmi.projectmanagment.IProject toDelete) throws RemoteException, IllegalStateException{
		deleteProject(toDelete.getProjectId());
	}

	@Override
	public void deleteProject(final String projectId) throws RemoteException, IllegalStateException{
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
	public void setNatures(edu.kit.palladio.rmi.projectmanagment.IProject projectToSetNatures, String[] natures) throws RemoteException,IllegalStateException, IllegalArgumentException {
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
	public edu.kit.palladio.rmi.projectmanagment.IProject getProject(final String projectId) throws RemoteException, IllegalStateException, IllegalArgumentException {
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		final IProject  resultProject = workspaceRoot.getProject(projectId);
		return toProjectManagmentIProject(resultProject);
	}
	
	private edu.kit.palladio.rmi.projectmanagment.IProject toProjectManagmentIProject(final IProject projectToConvert) throws IllegalStateException, IllegalArgumentException{
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
				return new edu.kit.palladio.rmi.projectmanagment.Project(projectToConvert.getDescription().getName());
			} catch (CoreException e) {
				throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist or is not open.");
				
			}
		}
		throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist.");
	}

	@Override
	public edu.kit.palladio.rmi.projectmanagment.IProject createProject(
			edu.kit.palladio.rmi.projectmanagment.IProject projectToCreate) throws IllegalStateException, Throwable {
		return createProject(projectToCreate.getProjectId());
	}



}
