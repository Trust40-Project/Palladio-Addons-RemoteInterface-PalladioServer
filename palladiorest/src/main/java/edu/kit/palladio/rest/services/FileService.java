package edu.kit.palladio.rest.services;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.remote.filemanagement.File;
import edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload;

@Service
public class FileService implements IFileService {

    private IRemoteFileUpload fileUploader;

    @Autowired
    public FileService(IRemoteFileUpload fileUploader) {
        this.fileUploader = fileUploader;
    }

    @Override
    public void createFile(String path) {
        try {
            fileUploader.createFile(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void createDirectory(String path) {
        try {
            fileUploader.createDirectory(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String path) {
        try {
            fileUploader.deleteFile(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDirectory(String path) {
        try {
            fileUploader.deleteDirectory(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Collection<File> listFilesAndDirectories(String path) {
        try {
            return fileUploader.listFilesAndDirectories(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void write(String path, MultipartFile file) {
        try {
            fileUploader.write(path, file.getBytes());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Resource read(String path) {
        byte[] bytesRead = new byte[0];
        try {
            bytesRead = fileUploader.read(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ByteArrayResource(bytesRead);
    }
    
}