package edu.kit.palladio.remote.filemanagement;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class EclipseFileOutputStream extends OutputStream implements AppendOutputStream{
	
	private final IFile fileToWrite;
	private boolean append;
	private boolean firstDone = false;
	
	public EclipseFileOutputStream(final String path) {
		this(path, false);
	}
	
	public EclipseFileOutputStream(final String path, final boolean append) {
		this.append = append;
		this.fileToWrite = new FileManager().getFile(path);
	}

	@Override
	public void write(final int b) throws IOException {
		final byte lower = (byte)(b & 0xFF);
		write(new byte[] {lower});
		
	}
	
	public void write(final byte[] b) {
		final InputStream source = new ByteArrayInputStream(b);
		if(append || firstDone) {
			try {
				fileToWrite.appendContents(source,true, false, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				fileToWrite.setContents(source,true, false, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.firstDone = true;
		
	}
	
	
	
	
	
	@Override
	public void append(int b) throws IOException {
		this.append = true;
		this.write(b);
		
	}

	@Override
	public void append(byte[] b) throws IOException {
		this.append = true;
		this.write(b);
		
	}
	

}
