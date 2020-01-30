package edu.kit.palladio.rest.filemanagment;

import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.RemoteException;

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
    public IFileNode save(String path, MultipartFile file) throws RemoteException, IOException, IllegalArgumentException {
        final IFileNode fileToCreate = new File(file.getOriginalFilename(), file.getBytes());
        fileUploader.createFile(path, fileToCreate);
        return fileToCreate;   
    }

    @Override
    public IFileNode getSingleFileNode(String path) throws RemoteException, IllegalStateException {
        return fileUploader.getFileNode(path);
    }

    @Override
    public IFileNode getAllFileNode(String pathToStart) throws RemoteException, IllegalStateException {
        return fileUploader.getAllFileNodes(pathToStart);
    }

    @Override
    public IFileNode getAllFileNodesFromProject(String projectId) throws RemoteException, IllegalStateException {
        return fileUploader.getAllFileNodesFromProject(projectId);
    }

    @Override
    public void deleteFileNode(String pathToDeleteAt)
            throws RemoteException, IllegalArgumentException, IllegalStateException {
        fileUploader.deleteFileNode(pathToDeleteAt);

    }
}