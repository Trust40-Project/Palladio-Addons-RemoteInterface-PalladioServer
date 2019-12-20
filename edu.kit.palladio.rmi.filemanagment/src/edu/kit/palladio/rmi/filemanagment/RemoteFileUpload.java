package edu.kit.palladio.rmi.filemanagment;

import java.rmi.RemoteException;

public class RemoteFileUpload implements IRemoteFileUpload {

	public RemoteFileUpload() {
		super();
	}
	@Override
	public boolean createFile(String fileName, String fileContent) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void helloFileUplod() throws RemoteException {
		System.out.println("hello from file managment plugin");

	}

}
