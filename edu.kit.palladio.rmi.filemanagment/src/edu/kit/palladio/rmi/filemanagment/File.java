package edu.kit.palladio.rmi.filemanagment;

import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import edu.kit.palladio.rmi.filemanagment.FileNodeDepthFirstIterator;
import edu.kit.palladio.rmi.filemanagment.IFileNode;

public class File implements IFileNode {

    private String name;
    //TODO: content byte
    private String content;
    private String contentEncoding;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public File(String name, String content, String contentEncoding){
        this.name = name;
        this.content = content;
        this.contentEncoding = contentEncoding;
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
    public String getContent() throws RemoteException {
        return this.content;
    }

    @Override
    public String getContentEncoding() throws RemoteException {
        return this.contentEncoding;
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
    public Iterator<IFileNode> iterator() {
        return new FileNodeDepthFirstIterator(this);
    }
    



}