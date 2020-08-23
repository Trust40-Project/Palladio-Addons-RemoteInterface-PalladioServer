package edu.kit.palladio.remote.filemanagement;


public class File {
	private final String path;
	private final boolean isDirectory;
	
	public File(final String path, final boolean isDirectory){
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
