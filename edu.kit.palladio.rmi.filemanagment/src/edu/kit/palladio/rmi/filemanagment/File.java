package edu.kit.palladio.rmi.filemanagment;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import edu.kit.palladio.rmi.filemanagment.FileNodeDepthFirstIterator;
import edu.kit.palladio.rmi.filemanagment.IFileNode;

public class File implements IFileNode {

    private String name;
    private byte[] content;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public File( String name, byte[] content) {
        this.name = name;
        this.content = content;
    }
    
    @Override
    public byte[] getContent() throws RemoteException {
        return this.content;
    }

   
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public boolean isDirectory() throws RemoteException {
        return false;
    }

    @Override
    public void addChild(IFileNode node) throws RemoteException {
        // no op

    }

    @Override
    public Collection<IFileNode> getChildren() throws RemoteException {
        return null;
    }


    @Override
    public Iterator<IFileNode> iterator() {
        return new FileNodeDepthFirstIterator(this);
    }

    @Override
    public String toString() {
    	return this.name;
    }
    
    @Override
    public String toStringRecursive(String delimiter) {
    	return this.name;
    }
   
    



}