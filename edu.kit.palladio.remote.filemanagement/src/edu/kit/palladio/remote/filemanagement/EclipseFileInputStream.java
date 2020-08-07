package edu.kit.palladio.remote.filemanagement;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class EclipseFileInputStream extends InputStream {
	private final InputStream ofFileToRead;
	
	public EclipseFileInputStream(final String pathToReadFrom){
		final IFile toRead = new FileManager().getFile(pathToReadFrom);
		InputStream toReadFrom = null;
		try {
			toReadFrom = toRead.getContents();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ofFileToRead = toReadFrom;
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return ofFileToRead.read();
	}
	@Override
	public int read(final byte[] b)
	         throws IOException{
		return ofFileToRead.read(b);
	}
	
	@Override
	public int read(final byte[] b,
		       final int off,
		       final int len)
		         throws IOException{
		return ofFileToRead.read(b, off, len);
	}
	
	@Override
	public void close()
	           throws IOException{
		ofFileToRead.close();
	}
	
	@Override
	public boolean markSupported() {
		return ofFileToRead.markSupported();
	}
	
	@Override
	public void reset()
	           throws IOException{
		ofFileToRead.reset();
	}
	
	@Override
	public void mark(int readlimit) {
		ofFileToRead.mark(readlimit);
	}
	
	@Override
	public int available()
            throws IOException{
		return ofFileToRead.available();
	}
	
	@Override
	public long skip(long n)
	          throws IOException{
		return ofFileToRead.skip(n);
	}
	

}
