package edu.kit.palladio.rest.palladiorest;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import edu.kit.palladio.rest.filemanagment.FileService;
import edu.kit.palladio.rmi.filemanagment.IFileNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

@RestController
public class ProjectController {

    private FileService fileService;
    

    public ProjectController(@Autowired FileService fileService){
        this.fileService = fileService;
    }

    @Operation(summary = "Uploads a file into the file structure of the specified project. The path to upload to is defined through the request url after the projectId. If the file structure does not exist the missing directories will be created.")
    @PostMapping("/fileupload/project/{projectId}/**")
    public IFileNode newProjectContent(@Parameter(description = "Id of Palladio project to upload file to.", required = true) @PathVariable(name = "projectId") String projectId,
    @Parameter(description = "Files to upload to the project.", required =  true) @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        return fileService.save(path, file);
    }

    @Operation(summary = "Uploads multiple files into a directory in the file structure of the specified project. The path to upload to is defined through the request url after the projectId. If the file structure does not exist the missing directories will be created.")
    @PostMapping("/filesupload/project/{projectId}/**")
    public IFileNode[] newProjectMultiFileContent(@Parameter(description = "Id of Palladio project to upload files to.", required = true) @PathVariable(name = "projectId") String projectId, @Parameter(description = "Files to upload to the project.", required = true) @RequestParam(name = "files") MultipartFile[] files, HttpServletRequest request)
            throws RemoteException, IllegalArgumentException, IOException {
        IFileNode[] fileNodes = new IFileNode[files.length];
        for (int i = 0; i < files.length && i < fileNodes.length; i++) {
            fileNodes[i] = newProjectContent(projectId, files[i], request);
        }
        return fileNodes;
    }


     // Aggregate root
     @Operation(summary = "Get all files and directories of a Project.")
     @GetMapping("/project/{projectId}")
     IFileNode allFilesFromProject(@Parameter(description = "Id of project to get files and directories of.", required = true) @PathVariable(name = "projectId") String projectId, @Parameter(description = "Whether or not to include the content for each file. By default the content of files is not returned.", name = "content", required = false) @RequestParam(value = "content", defaultValue = "false") Boolean showFileContents) throws RemoteException, IllegalStateException {
        return fileService.getAllFileNodesFromProject(projectId, showFileContents);//TODO: if result null then not found.
     }
 
    
 
     // Single item
     @Operation(summary = "Get a file or directory at a certain location inside a project. The path to the file or directory of interest is specified in the request url after the project id.")
     @GetMapping("/project/{projectId}/**")
     IFileNode oneFileInProject(@Parameter(description = "Id of the project that contains the file or directory of interest.", required = true) @PathVariable(name = "projectId") String projectId,@Parameter(description = "Whether or not to include the content for the file or the children files of the directory. By default the content at the path's location is not returned.", name = "content", required = false) @RequestParam(value = "content", defaultValue = "false") Boolean showContent, HttpServletRequest request) throws RemoteException, IllegalStateException {

        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        return fileService.getSingleFileNode(path, showContent); //TODO: if result null then not found.
 
     }
 /*
     @PutMapping("/project/{projectId}/**")
     IFileNode updateFileInProject(@RequestBody IProject newEmployee, @PathVariable Long id) {
         
     }*/
 
     @Operation(summary = "Delete one file or directory with all its children files from a project. The path to the file or directory to delete is specified in the request url after the project id.")
     @DeleteMapping("/project/{projectId}/**")
     void deleteFileFromProject(@Parameter(description = "Id of the project that contains the file or directory to delete.", required = true) @PathVariable(name = "projectId") String projectId, HttpServletRequest request) throws RemoteException {
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        fileService.deleteFileNode(path);
         
     }

}