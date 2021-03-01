package edu.kit.palladio.remote.filemanagement;

import java.io.InputStream;
import java.util.Collection;

public interface IFileManager {
	void createFile(String path);
	void createDirectory(String path);
	void delete(String path);
	Collection<File> listFilesAndDirectories(String path);
	void uploadFile(String path, InputStream toUpload);
}
