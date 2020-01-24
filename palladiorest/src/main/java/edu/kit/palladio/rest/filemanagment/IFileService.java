package edu.kit.palladio.rest.filemanagment;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.rmi.filemanagment.IFileNode;

public interface IFileService {

    IFileNode save(final String path, final MultipartFile file) throws IOException, IllegalStateException;
}