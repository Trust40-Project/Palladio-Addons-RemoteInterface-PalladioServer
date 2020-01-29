package edu.kit.palladio.rmi.filemanagment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;

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
	public boolean createFiles(IFileNode projectRoot) throws IOException {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		if (!projectRoot.isDirectory()) {
			throw new IllegalArgumentException("Need a directory to create inside and " + projectRoot.getName() + " is not a directory.");
		}
		IProject projectToCreateIn = workspaceRoot.getProject(projectRoot.getName());
		if(!projectToCreateIn.exists()) {
			throw new IllegalArgumentException("Can only create inside of a project and " + projectRoot.getName() + " is not a project.");
		}
		boolean couldCreateAll = true;
		LinkedList<String> notCreatedFolderIds = new LinkedList<String>();
		LinkedList<String> notCreatedFileIds = new LinkedList<String>();
		for (IFileNode child : projectRoot.getChildren()) {
			if (child.isDirectory()) {
				IFolder currentFolder = projectToCreateIn.getFolder(child.getName());
				try {
					if (!currentFolder.exists()) {

						currentFolder.create(false, true, new NullProgressMonitor());
					}

					if(!createRecursively(currentFolder, child.getChildren())) {
						couldCreateAll = false;
					}
				} catch (CoreException e) {
					couldCreateAll = false;
					notCreatedFolderIds.add(child.getName());
				}
			} else {
				IFile currentFile = projectToCreateIn.getFile(child.getName());
				InputStream source = new ByteArrayInputStream(child.getContent());
				if (!currentFile.exists()) {
					// create a new file.
					try {
						currentFile.create(source, false, new NullProgressMonitor());
					} catch (CoreException e) {
						couldCreateAll = false;
						notCreatedFileIds.add(child.getName());
					}
				} else {
					// replace content of current file.
					try {
						currentFile.setContents(source, false, true, new NullProgressMonitor());
					} catch (CoreException e) {
						couldCreateAll = false;
						notCreatedFileIds.add(child.getName());
					}
				}
			}
		}
		if(!notCreatedFolderIds.isEmpty() || !notCreatedFileIds.isEmpty()) {
			throw new IOException("Could not create the following folders: " + notCreatedFolderIds.toString() + " and files: " + notCreatedFileIds.toString() + ".");
		}
		return couldCreateAll;
	}

	private boolean createRecursively(IFolder parentFolder, Collection<IFileNode> childrenToCreate)
			throws RemoteException {
		boolean couldCreateAll = true;
		
		for (IFileNode child : childrenToCreate) {
			if (child.isDirectory()) {
				IFolder currentFolder = parentFolder.getFolder(child.getName());
				try {
					if (!currentFolder.exists()) {

						currentFolder.create(false, true, new NullProgressMonitor());

					}
					if (!createRecursively(currentFolder, child.getChildren())) {
						couldCreateAll = false;
					}
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					couldCreateAll = false;
				}
			} else {
				IFile currentFile = parentFolder.getFile(child.getName());
				InputStream source = new ByteArrayInputStream(child.getContent());
				if (!currentFile.exists()) {
					// create a new file.
					try {
						currentFile.create(source, false, new NullProgressMonitor());
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						couldCreateAll = false;
					}
				} else {
					// replace content of current file.
					try {
						currentFile.setContents(source, false, true, new NullProgressMonitor());
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						couldCreateAll = false;
					}
				}
			}

		}
		return couldCreateAll;
	}

	@Override
	public boolean createFile(String path, IFileNode file) throws IOException {
		
		if(file.isDirectory()) {
			return false;
		}
		Path pathToParse = FileSystems.getDefault().getPath(path);
		Path fileName = pathToParse.getFileName();
		if(fileName == null) {
			// the path has 0 elements.
			// we are not allowed to create in workspace. Thus path must at least contain a project folder.
			return false;
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
		
		return this.createFiles(toCreate);
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
