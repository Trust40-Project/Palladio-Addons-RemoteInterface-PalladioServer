package edu.kit.palladio.rmi.filemanagment;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFileUpload extends Remote {
	
	/**
	 * Creates all the files and directories inside the given project. If projectRoot is a project open in the
	 * workspace then all the files and directories that are children of projectRoot get created if they do not exist
	 * or have their content updated.
	 * @param projectRoot a file node directory representing the eclipse project in the workspace the file system structure is supposed to be created in.
	 * @return true on success and false if there is at least one directory or file that could not be created.
	 * @throws RemoteException
	 * @throws IOException 
	 */
	void createFiles(IFileNode projectRoot) throws RemoteException, IOException;
	void createFile(String path, IFileNode file) throws RemoteException, IOException, IllegalArgumentException;
}
