package edu.kit.palladio.rest.filemanagment;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.rmi.filemanagment.File;
import edu.kit.palladio.rmi.filemanagment.IFileNode;
import edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload;

@Service
public class FileService implements IFileService {

    private IRemoteFileUpload fileUploader;

    @Autowired
    public FileService(IRemoteFileUpload fileUploader) {
        this.fileUploader = fileUploader;
    }

    @Override
    public IFileNode save(String path, MultipartFile file) throws IOException, IllegalStateException {
        final IFileNode fileToCreate = new File(file.getOriginalFilename(), file.getBytes());
        if(fileUploader.createFile(path, fileToCreate)){
            // success.
            return fileToCreate;
        } else {
            throw new IllegalStateException("Please make sure that the path is valid.");
        }
    }
}