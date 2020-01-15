package edu.kit.palladio.rmi.filemanagment;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileNodeIterator implements Iterator {

	private Deque<IFileNode> toDiscover;
	
	FileNodeIterator(IFileNode startNode) throws IllegalArgumentException{
		assert(startNode != null);
		toDiscover = new ArrayDeque<IFileNode>();
		if(!toDiscover.offer(startNode)) {
			throw new IllegalArgumentException("Could not start with given file node.");
		}
	}
	
	@Override
	public boolean hasNext() {
		return !toDiscover.isEmpty();
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if (!hasNext()) {
            throw new NoSuchElementException();
        }
		IFileNode visit = toDiscover.pollLast();
		try {
			if(visit.isDirectory()) {
				toDiscover.addAll(visit.getChildren());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return visit;
	}

}
