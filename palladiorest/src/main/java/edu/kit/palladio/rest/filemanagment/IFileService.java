package edu.kit.palladio.rest.filemanagment;

import java.io.IOException;
import java.rmi.RemoteException;

import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.rmi.filemanagment.IFileNode;

public interface IFileService {

    IFileNode save(final String path, final MultipartFile file) throws RemoteException, IOException, IllegalArgumentException;

    IFileNode getSingleFileNode(final String path) throws RemoteException, IllegalStateException;

    IFileNode getAllFileNode(final String pathToStart) throws RemoteException, IllegalStateException;

    IFileNode getAllFileNodesFromProject(final String projectId) throws RemoteException, IllegalStateException;

    void deleteFileNode(final String pathToDeleteAt) throws RemoteException, IllegalArgumentException, IllegalStateException;
}