package edu.kit.palladio.rmi.filemanagment;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IFileNode extends Remote, Serializable, Iterable<IFileNode> {
	String getName() throws RemoteException;
	boolean isDirectory() throws RemoteException;
	void addChild(IFileNode node) throws RemoteException;
	Collection<IFileNode> getChildren() throws RemoteException;
	String getContent() throws RemoteException;
	Charset getContentEncoding() throws RemoteException;
}
