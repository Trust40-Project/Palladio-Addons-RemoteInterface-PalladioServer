package edu.kit.palladio.rest.palladiorest.controllers;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import edu.kit.palladio.rest.services.FileService;
import edu.kit.palladio.rest.services.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class FileController {
    private IFileService fileManager;
    
    @Autowired
    public FileController( FileService fileManager){
        this.fileManager = fileManager;
    }

    @Operation(summary = "Creates a new empty file in the specified project.")
    @PutMapping("files/project/{projectId}/**")
    public void createFile(@Parameter(description = "Id of Palladio project to create file in.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        fileManager.createFile(path);
    }

    @Operation(summary = "Deletes a file in the specified project.")
    @DeleteMapping("files/project/{projectId}/**")
    public void deleteFile(@Parameter(description = "Id of Palladio project to delete file from.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        fileManager.deleteFile(path);
    }

  

    @Operation(summary = "Downloads a file from the specified project.")
    @GetMapping("download/files/project/{projectId}/**")
    public ResponseEntity<Resource> downloadFile(@Parameter(description = "Id of Palladio project to download file from.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
        Resource resource = fileManager.read(path);
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(resource.contentLength())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }

    @Operation(summary = "Uploads a file to a file of the specified project. The path to upload to is defined through the request url after the projectId. The file needs to exist.")
    @PutMapping("upload/files/project/{projectId}/**")
    public void newProjectContent(@Parameter(description = "Id of Palladio project to upload file to.", required = true) @PathVariable(name = "projectId") String projectId,
    @Parameter(description = "File to upload to the project.", required =  true) @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        fileManager.write(path, file);;
    }
}