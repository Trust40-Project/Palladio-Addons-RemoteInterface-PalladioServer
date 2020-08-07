package edu.kit.palladio.rest.services;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


public interface IFileService {

    void createFile(String path);
	void createDirectory(String path);
    void deleteFile(String path);
    void deleteDirectory(String path);
    Collection<edu.kit.palladio.remote.filemanagement.File> listFilesAndDirectories(String path);
    void write(final String path, final MultipartFile file);
    Resource read(final String path);
}