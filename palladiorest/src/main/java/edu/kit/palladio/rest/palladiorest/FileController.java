package edu.kit.palladio.rest.palladiorest;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.kit.palladio.rest.filemanagment.FileService;
import edu.kit.palladio.rmi.filemanagment.IFileNode;
import edu.kit.palladio.rmi.projectmanagment.IProject;

@RestController
public class FileController {
    private FileService fileService;

    public FileController(@Autowired FileService fileService) {
        this.fileService = fileService;
    }

    // Aggregate root
    @GetMapping("/files")
    List<IProject> allFiles() throws RemoteException {
        return null;

    }

    @PostMapping("/files")
    IFileNode newFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "path") String path) throws IOException {
        return fileService.save(path, file);
    }

    // Single item

    @GetMapping("/files/{id}")
    IProject oneFile(@PathVariable String projectId) throws RemoteException {
        return null;

    }
/*
    @PutMapping("/projects/{id}")
    Employee updateProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
        
    }*/

    @DeleteMapping("/file/{projectId}")
    void deleteFile(@PathVariable String projectId) throws RemoteException {
        
        
    }
}