package edu.kit.palladio.rmi.filemanagment;

import edu.kit.palladio.rcp.api.ILoadMe;
import edu.kit.palladio.remote.filemanagement.EclipseFileInputStream;
import edu.kit.palladio.remote.filemanagement.EclipseFileOutputStream;
import edu.kit.palladio.remote.filemanagement.File;
import edu.kit.palladio.remote.filemanagement.FileManager;
import edu.kit.palladio.remote.filemanagement.IFileManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, property = { "id=edu.kit.palladio.rmi.filemanagment.RemoteFileUpload", "name=Remote File Upload"})
public class RemoteFileUpload implements IRemoteFileUpload, ILoadMe {
	private static final String RMIID = "edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload";
	private final IFileManager fileManager;
	public RemoteFileUpload() {
		this.fileManager = new FileManager();

	}

	

	@Override
	public String getRmiId() throws RemoteException {
		return RMIID;
	}



	@Override
	public void createFile(String path) throws RemoteException {
		this.fileManager.createFile(path);
		
	}



	@Override
	public void createDirectory(String path) throws RemoteException {
		this.fileManager.createDirectory(path);
		
	}



	@Override
	public void deleteFile(String path) throws RemoteException {
		this.fileManager.delete(path);
		
	}



	@Override
	public void deleteDirectory(String path) throws RemoteException {
		this.fileManager.delete(path);
		
	}



	@Override
	public Collection<File> listFilesAndDirectories(String path) throws RemoteException {
		return this.fileManager.listFilesAndDirectories(path);
	}



	@Override
	public void write(String path, byte[] file) throws RemoteException {
		try(final OutputStream streamToWrite = new EclipseFileOutputStream(path)){
			try {
				streamToWrite.write(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}



	@Override
	public byte[] read(String path) throws RemoteException {
		try(final InputStream streamToRead = new EclipseFileInputStream(path)){
			try {
				return streamToRead.readAllBytes();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new byte[0];
	}

	

	

}
