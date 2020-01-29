package edu.kit.palladio.rmi.filemanagment;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

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
	public byte[] getContent() throws RemoteException {
		return new byte[0];
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
	
	@Override
	public String toString() {
		return toStringRecursive("\n	");
	}
	
	public String toStringRecursive(String delimiter) {
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		try {
			stringJoiner.add(this.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for(IFileNode child : this.getChildren()) {
				stringJoiner.add(child.toStringRecursive(delimiter + "	"));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringJoiner.toString();
	}
	

    
}