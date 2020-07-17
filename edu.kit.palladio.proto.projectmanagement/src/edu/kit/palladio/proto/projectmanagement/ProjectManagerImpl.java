package edu.kit.palladio.proto.projectmanagement;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import io.grpc.Status;

public class ProjectManagerImpl extends ProjectManagerGrpc.ProjectManagerImplBase{
	
	private transient IWorkspace workspace;

	public ProjectManagerImpl() {
		this.workspace = ResourcesPlugin.getWorkspace();
	}
		public void createProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.CreateProjectResponse> responseObserver) {
			
			
			final IWorkspaceRoot workspaceRoot = workspace.getRoot();
			final org.eclipse.core.resources.IProject newProject = workspaceRoot.getProject(request.getProjectId());
			
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			try {
				newProject.create(progressMonitor);
			} catch (CoreException e) {
				responseObserver.onError(handleProjectCreationError(newProject));
				responseObserver.onCompleted();
				return;
			} 
			try {
				//Open project to be able to use it in the future.
				newProject.open(progressMonitor); 
			} catch (CoreException e) {
				responseObserver.onError(Status.ABORTED.augmentDescription("resource change not allowed.").asRuntimeException());
				responseObserver.onCompleted();
				return;
			} 
			
			CreateProjectResponse reply = CreateProjectResponse.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}

		private Throwable handleProjectCreationError(final org.eclipse.core.resources.IProject projectToCreate) {
			IStatus status = workspace.validateName(projectToCreate.getName(), IResource.PROJECT);
			if(status.isOK()) {
				status = workspace.validateProjectLocation(projectToCreate, projectToCreate.getFullPath());
			} 
			
			if(!status.isOK()) {
				return Status.INVALID_ARGUMENT.augmentDescription(status.getMessage()).asRuntimeException();
			} 
			return Status.INVALID_ARGUMENT.augmentDescription("the project " + projectToCreate.getName() + " already exists or the project description file could not be created.").asRuntimeException();
			
		}
		
		/**
		 */
		public void deleteProject(edu.kit.palladio.proto.projectmanagement.Project request,
				io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse> responseObserver) {
			
			final IWorkspaceRoot workspaceRoot = workspace.getRoot();
			final org.eclipse.core.resources.IProject projectToDelete = workspaceRoot.getProject(request.getProjectId());
			final IProgressMonitor progressMonitor = new NullProgressMonitor();
			try {
				projectToDelete.delete(org.eclipse.core.resources.IResource.ALWAYS_DELETE_PROJECT_CONTENT | org.eclipse.core.resources.IResource.FORCE, progressMonitor);
			} catch (CoreException e) {
				responseObserver.onError(Status.ABORTED.augmentDescription("could not delete project with id " + request.getProjectId()).asRuntimeException());
				responseObserver.onCompleted();
				return;
			}
			DeleteProjectResponse reply = DeleteProjectResponse.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}

		

		
		
		 public void getProjects(edu.kit.palladio.proto.projectmanagement.GetProjectsRequest request,
			        io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project> responseObserver) {
			 	final IWorkspaceRoot workspaceRoot = workspace.getRoot();
				final org.eclipse.core.resources.IProject[] projects = workspaceRoot.getProjects();
				
				for(int i = 0; i < projects.length; i++) {
					try {
						responseObserver.onNext(toProject(projects[i]));
					}catch( IllegalArgumentException e) {
						responseObserver.onError(Status.NOT_FOUND.augmentDescription(e.getMessage()).asRuntimeException());
					} catch(IllegalStateException e) {
						responseObserver.onError(Status.ABORTED.augmentDescription(e.getMessage()).asRuntimeException());
					}
					
					
				}
				responseObserver.onCompleted();
		 }
		 /**
			 */
			public void getProject(edu.kit.palladio.proto.projectmanagement.Project request,
					io.grpc.stub.StreamObserver<edu.kit.palladio.proto.projectmanagement.Project> responseObserver) {
				final IWorkspaceRoot workspaceRoot = workspace.getRoot();
				final IProject  resultProject = workspaceRoot.getProject(request.getProjectId());
				
				try {
					responseObserver.onNext(toProject(resultProject));
				}catch( IllegalArgumentException e) {
					responseObserver.onError(Status.NOT_FOUND.augmentDescription(e.getMessage()).asRuntimeException());
				} catch(IllegalStateException e) {
					responseObserver.onError(Status.ABORTED.augmentDescription(e.getMessage()).asRuntimeException());
				}
				responseObserver.onCompleted();
			}
		 
		 private Project toProject(final IProject projectToConvert) throws IllegalStateException, IllegalArgumentException{
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
						return Project.newBuilder().setProjectId(projectToConvert.getDescription().getName()).build();
					} catch (CoreException e) {
						throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist or is not open.");
						
					}
				}
				throw new IllegalArgumentException("the project with id " + projectToConvert.getName() + " does not exist.");
			}
	}