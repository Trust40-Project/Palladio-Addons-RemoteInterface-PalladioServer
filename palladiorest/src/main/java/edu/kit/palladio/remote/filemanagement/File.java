package edu.kit.palladio.remote.filemanagement;

import java.io.Serializable;

public class File implements Serializable {
    private static final long serialVersionUID = -3438010532841348771L;
    private final String path;
	private final boolean isDirectory;
	
	File(final String path, final boolean isDirectory){
		this.path = path;
		this.isDirectory = isDirectory;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public String getPath() {
		return path;
	}
}
