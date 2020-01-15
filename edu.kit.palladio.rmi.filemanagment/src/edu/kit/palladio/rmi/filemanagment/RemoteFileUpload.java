package edu.kit.palladio.rmi.filemanagment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class RemoteFileUpload implements IRemoteFileUpload {

	private transient IWorkspace workspace;

	public RemoteFileUpload() {
		super();
		this.workspace = ResourcesPlugin.getWorkspace();
		System.out.println("workspace root: " + this.workspace.getRoot().getRawLocation());
	}

	@Override
	public boolean createFiles(IFileNode projectRoot) throws RemoteException {
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		if (!projectRoot.isDirectory()) {
			return false;
		}
		IProject projectToCreateIn = workspaceRoot.getProject(projectRoot.getName());
		boolean couldCreateAll = true;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
					couldCreateAll = false;
				}
			} else {
				IFile currentFile = projectToCreateIn.getFile(child.getName());
				InputStream source = new ByteArrayInputStream(child.getContent().getBytes(child.getContentEncoding()));
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
				InputStream source = new ByteArrayInputStream(child.getContent().getBytes(child.getContentEncoding()));
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

}
