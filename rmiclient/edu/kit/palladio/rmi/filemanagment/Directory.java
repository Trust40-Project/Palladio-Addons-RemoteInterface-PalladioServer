package edu.kit.palladio.rmi.filemanagment;

import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.kit.palladio.rmi.filemanagment.FileNodeDepthFirstIterator;
import edu.kit.palladio.rmi.filemanagment.IFileNode;

public class Directory implements IFileNode{

    private ArrayList<IFileNode> children;
    private String name;

	/**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
    public Directory(String name){
        this.name = name;
        this.children = new ArrayList<IFileNode>();
    }

	@Override
	public void addChild(IFileNode node) throws RemoteException {
		if(!this.children.contains(node)){
            this.children.add(node);
        }
		
	}

	@Override
	public Collection<IFileNode> getChildren() throws RemoteException {
		return this.children;
	}

	@Override
	public String getContent() throws RemoteException {
		return null;
	}

	@Override
	public String getContentEncoding() throws RemoteException {
		return null;
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public boolean isDirectory() throws RemoteException {
		return true;
	}

	@Override
	public Iterator<IFileNode> iterator() {
		return new FileNodeDepthFirstIterator(this);
	}

    
}