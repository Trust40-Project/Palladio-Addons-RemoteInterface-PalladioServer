package edu.kit.palladio.proto.rest.controllers;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import edu.kit.palladio.remote.filemanagement.File;
import edu.kit.palladio.proto.rest.services.FilemanagementClient;
import edu.kit.palladio.proto.rest.services.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ProjectController {

    private IFileService fileManager;
    
    @Autowired
    public ProjectController( FilemanagementClient fileManager){
        this.fileManager = fileManager;
    }
    
    @Operation(summary = "List files and directories at path inside the specified project.")
    @GetMapping("ls/project/{projectId}/**")
    public Collection<File> listFilesAndDiretories(@Parameter(description = "Id of Palladio project to list files and directories from.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
       
        return this.fileManager.listFilesAndDirectories(path);
    }
}