package edu.kit.palladio.proto.rest.controllers;


import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import edu.kit.palladio.proto.rest.services.FilemanagementClient;
import edu.kit.palladio.proto.rest.services.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class DirectoryController {
    private IFileService fileManager;
    
    @Autowired
    public DirectoryController( FilemanagementClient fileManager){
        this.fileManager = fileManager;
    }

    @Operation(summary = "Creates a new empty directory in the specified project.")
    @PutMapping("directories/project/{projectId}/**")
    public void createDirectory(@Parameter(description = "Id of Palladio project to create directory in.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        fileManager.createDirectory(path);
    }

    @Operation(summary = "Deletes a directory from the specified project.")
    @DeleteMapping("directories/project/{projectId}/**")
    public void deleteFile(@Parameter(description = "Id of Palladio project to delete directory from.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        fileManager.deleteDirectory(path);
    }
}