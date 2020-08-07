package edu.kit.palladio.rmi.filemanagment;

import edu.kit.palladio.remote.filemanagement.File;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;


public interface IRemoteFileUpload extends Remote {
	 void createFile(String path) throws RemoteException;
		void createDirectory(String path) throws RemoteException;
	    void deleteFile(String path) throws RemoteException;
	    void deleteDirectory(String path) throws RemoteException;
	    Collection<File> listFilesAndDirectories(String path) throws RemoteException;
	    void write(final String path, final byte[] file) throws RemoteException;
	    byte[] read(final String path) throws RemoteException;
}
