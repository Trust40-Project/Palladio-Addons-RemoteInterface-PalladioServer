package edu.kit.palladio.remote.filemanagement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, property = { "id=edu.kit.palladio.remote.filemanagement", "name=File Manager"})
public class FileManager implements IFileManager {
	private final transient IWorkspace workspace = ResourcesPlugin.getWorkspace();
	
	protected IFile getFile(String pathString) {
		final IPath path = org.eclipse.core.runtime.Path.fromPortableString(pathString);
		final IProject projectContainingFile = getProjectToCreateIn(path);
		
		IFile file = projectContainingFile.getFile(path.removeFirstSegments(1));
    	
		if(!file.exists()) {
			//TODO error file does not exist
		}  	
		return file;
	}

	 private IProject getProjectToCreateIn(final IPath path) {
			if(path.isEmpty() || path.isRoot() || path.segmentCount() == 1) {
				//TODO: can only create inside project and not in root.
			}
			final IWorkspaceRoot workspaceRoot = workspace.getRoot();
			
			final IProject projectToCreateIn = workspaceRoot.getProject(path.segment(0));
			if(!projectToCreateIn.exists()) {
				//TODO: can only create inside project and not in root.
			}
			if(!projectToCreateIn.isOpen()) {
				try {
					projectToCreateIn.open(new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return projectToCreateIn;
	    }

	 
	@Override
	public void createFile(String pathOfNewFile) {
		final IPath path = org.eclipse.core.runtime.Path.fromPortableString(pathOfNewFile);
		final IProject projectToCreateIn = getProjectToCreateIn(path);
		final IPath pathWithoutProject  =path.removeFirstSegments(1);
		for(int i =1; i < pathWithoutProject.segmentCount(); ++i) {
			IFolder currentFolder = projectToCreateIn.getFolder(pathWithoutProject.uptoSegment(i));
			if(!currentFolder.exists()) {
				try {
					currentFolder.create(false, true, new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
			IFile currentFile = projectToCreateIn.getFile(pathWithoutProject.uptoSegment(pathWithoutProject.segmentCount()));
			if(!currentFile.exists()) {
				try {
					currentFile.create(InputStream.nullInputStream(), true, new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		

	}

	@Override
	public void createDirectory(String pathOfNewDirectory) {
		final IPath path = org.eclipse.core.runtime.Path.fromPortableString(pathOfNewDirectory);
		final IProject projectToCreateIn = getProjectToCreateIn(path);
		final IPath pathWithoutProject = path.removeFirstSegments(1);
		for(int i =1; i <= pathWithoutProject.segmentCount(); ++i) {
			IFolder currentFolder = projectToCreateIn.getFolder(pathWithoutProject.uptoSegment(i));
			if(!currentFolder.exists()) {
				try {
					currentFolder.create(false, true, new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			

	}

	@Override
	public void delete(String pathOfResourceToDelete) {
		final IPath path = org.eclipse.core.runtime.Path.fromPortableString(pathOfResourceToDelete);
	
		//TODO: Should prevent from deleting project folder?
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IFile fileToDelete = workspaceRoot.getFile(path);
		if(fileToDelete.exists()) {
			try {
				fileToDelete.delete(true, false, new NullProgressMonitor());
				return;
			} catch (CoreException e) {
				e.printStackTrace();
				throw new IllegalStateException("Currently the file at " + pathOfResourceToDelete + " can not be deleted.");
			}
		}
		
		IFolder folderToDelete = workspaceRoot.getFolder(path);
		if(folderToDelete.exists()) {
			try {
				folderToDelete.delete(true, false, new NullProgressMonitor());
				return;
			} catch (CoreException e) {
				throw new IllegalStateException("Currently the folder at " + pathOfResourceToDelete + " can not be deleted.");
			}
		}
		throw new IllegalArgumentException(pathOfResourceToDelete + " is not a valid resource to delete.");
			
	}

	@Override
	public Collection<File> listFilesAndDirectories(String pathOfDirectoryToListChildren) {
		final IPath path = org.eclipse.core.runtime.Path.fromPortableString(pathOfDirectoryToListChildren);
		final IProject projectContainingPath = getProjectToCreateIn(path);
		/*final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IFolder folderToList = workspaceRoot.getFolder(path);*/
		if(!projectContainingPath.exists()) {
			throw new IllegalArgumentException(pathOfDirectoryToListChildren + " is not inside an existing project.");
		}
		IResource[] resources = new IResource[0];
		if(path.segmentCount() > 1) {
			IPath withoutProject =path.removeFirstSegments(1);
			final IFolder folderToList = projectContainingPath.getFolder(withoutProject);
			if(!folderToList.exists()) {
				throw new IllegalArgumentException(pathOfDirectoryToListChildren + " is not an existing directory.");
				
			}
			try {
				resources = folderToList.members();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				resources = projectContainingPath.members();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		final List<File> filesAndDirectories = new ArrayList<>();
		for(IResource member :resources) {
			if(member.exists()) {
				if(member.getType() == org.eclipse.core.resources.IResource.FILE) {
					filesAndDirectories.add(new File(member.getFullPath().toPortableString(), false));
				} else if(member.getType() == org.eclipse.core.resources.IResource.FOLDER) {
					filesAndDirectories.add(new File(member.getFullPath().toPortableString(), true));
				}
			}
		}
		return filesAndDirectories;
	}

}
