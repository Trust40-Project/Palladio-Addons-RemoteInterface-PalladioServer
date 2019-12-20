package edu.kit.palladio.rmi.filemanagment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFileUpload extends Remote {
	boolean createFile(String fileName, String fileContent) throws RemoteException;
	void helloFileUplod()throws RemoteException;
}
