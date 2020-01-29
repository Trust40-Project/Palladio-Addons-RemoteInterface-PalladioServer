package edu.kit.palladio.rmi.filemanagment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class RemoteFileUpload implements IRemoteFileUpload {
	
	//TODO: implement get file system structure.

	private transient IWorkspace workspace;

	public RemoteFileUpload() {
		super();
		this.workspace = ResourcesPlugin.getWorkspace();
		System.out.println("workspace root: " + this.workspace.getRoot().getRawLocation());
	}

	@Override
	public void createFiles(IFileNode projectRoot) throws IOException {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		if (!projectRoot.isDirectory()) {
			throw new IllegalArgumentException("Need a directory to create inside and " + projectRoot.getName() + " is not a directory.");
		}
		IProject projectToCreateIn = workspaceRoot.getProject(projectRoot.getName());
		
		if(!projectToCreateIn.exists()) {
			throw new IllegalArgumentException("Can only create inside of a project and " + projectRoot.getName() + " is not a project.");
		}
		
		final IFileNode couldNotCreate = new Directory(projectRoot.getName());
		for (IFileNode child : projectRoot.getChildren()) {
			if (child.isDirectory()) {
				IFolder currentFolder = projectToCreateIn.getFolder(child.getName());
				try {
					if (!currentFolder.exists()) {

						currentFolder.create(false, true, new NullProgressMonitor());
					}
					IFileNode couldNotCreateRecursive = createRecursively(currentFolder, child.getChildren());
					if(couldNotCreateRecursive != null) {
						couldNotCreate.addChild(couldNotCreateRecursive);
					}
				} catch (CoreException e) {
					couldNotCreate.addChild(child);
				}
			} else {
				IFile currentFile = projectToCreateIn.getFile(child.getName());
				InputStream source = new ByteArrayInputStream(child.getContent());
				if (!currentFile.exists()) {
					// create a new file.
					try {
						currentFile.create(source, false, new NullProgressMonitor());
					} catch (CoreException e) {
						couldNotCreate.addChild(child);
					}
				} else {
					// replace content of current file.
					try {
						currentFile.setContents(source, false, true, new NullProgressMonitor());
					} catch (CoreException e) {
						couldNotCreate.addChild(child);
					}
				}
			}
		}
		/*if(!notCreatedFolderIds.isEmpty() || !notCreatedFileIds.isEmpty()) {
			throw new IOException("Could not create the following folders: " + notCreatedFolderIds.toString() + " and files: " + notCreatedFileIds.toString() + ".");
		}*/
		if(!couldNotCreate.getChildren().isEmpty()) {
			throw new IOException("Could not create the following part of the file structure:\n" + couldNotCreate.toString());
		}
		//return couldCreateAll;
	}

	private IFileNode createRecursively(IFolder parentFolder, Collection<IFileNode> childrenToCreate)
			throws RemoteException {
		
		//boolean couldCreateAll = true;
		final IFileNode couldNotCreate = new Directory(parentFolder.getName());
		if(!parentFolder.exists()) {
			return couldNotCreate;
		}
		for (IFileNode child : childrenToCreate) {
			if (child.isDirectory()) {
				IFolder currentFolder = parentFolder.getFolder(child.getName());
				try {
					if (!currentFolder.exists()) {

						currentFolder.create(false, true, new NullProgressMonitor());

					}
					IFileNode recursiveCouldNotCreate = createRecursively(currentFolder, child.getChildren());
					if(recursiveCouldNotCreate != null) {
						// recursive create did not go well.
						couldNotCreate.addChild(recursiveCouldNotCreate);
					}
					
				} catch (CoreException e) {
					couldNotCreate.addChild(child);
				}
			} else {
				IFile currentFile = parentFolder.getFile(child.getName());
				InputStream source = new ByteArrayInputStream(child.getContent());
				if (!currentFile.exists()) {
					// create a new file.
					try {
						currentFile.create(source, false, new NullProgressMonitor());
					} catch (CoreException e) {
						couldNotCreate.addChild(child);
					}
				} else {
					// replace content of current file.
					try {
						currentFile.setContents(source, false, true, new NullProgressMonitor());
					} catch (CoreException e) {
						couldNotCreate.addChild(child);
					}
				}
			}

		}
		if(couldNotCreate.getChildren().isEmpty()) {
			return null;
		} else {
			return couldNotCreate;
		}
		
	}

	@Override
	public void createFile(String path, IFileNode file) throws IOException,IllegalArgumentException {
		
		if(file.isDirectory()) {
			throw new IllegalArgumentException("The path is not valid. Can only create inside a existing project directory.");
		}
		Path pathToParse = FileSystems.getDefault().getPath(path);
		Path fileName = pathToParse.getFileName();
		if(fileName == null) {
			// the path has 0 elements.
			// we are not allowed to create in workspace. Thus path must at least contain a project folder.
			throw new IllegalArgumentException("The path is not valid. Can only create inside a existing project.");
		}
		if(!fileName.toString().equals(file.getName())) {
			// the file to create is not yet part of the path.
			pathToParse = FileSystems.getDefault().getPath(path, file.getName());
		}
		/*if(path.endsWith(file.getName()) || path.endWith(file.getName())) {
			pathToParse = FileSystems.getDefault().getPath(path);
		} else {
			pathToParse = FileSystems.getDefault().getPath(path, file.getName());
		}*/
		final IFileNode toCreate = parsePathToTree(pathToParse, file);
		this.createFiles(toCreate);
	}
	
	/**
	 * Creates a nested IFileNode structure with directories and possibly a file representing the given <i>path</i>.
	 * 
	 * @param path that is supposed to be created. The öast element needs to be fileAtLeaf.
	 * @param fileAtLeaf the directory or file to put at the end of the path.
	 * @return a representation of the path.
	 * @throws RemoteException
	 */
	private IFileNode parsePathToTree(final Path path, IFileNode fileAtLeaf) throws RemoteException {
		if(path.getNameCount() > 1) {
			IFileNode root = new Directory(path.getName(0).toString());
			IFileNode lastParent = root;
			for(int i = 1; i < path.getNameCount() - 1; ++i) {
				IFileNode directory = new Directory(path.getName(i).toString());
				lastParent.addChild(directory);
				lastParent = directory;
			}
			lastParent.addChild(fileAtLeaf);
			return root;
		} else {
			return fileAtLeaf;
		}
		
	}

}
