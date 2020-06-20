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

@RestController
public class ProjectController {

    private FileService fileService;
    

    public ProjectController(@Autowired FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/fileupload/project/{projectId}/**")
    public IFileNode newProjectContent(@PathVariable(name = "projectId") String projectId,
            @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        return fileService.save(path, file);
    }

   @PostMapping("/filesupload/project/{projectId}/**")
    public IFileNode[] newProjectMultiFileContent(@PathVariable(name = "projectId") String projectId, @RequestParam("files") MultipartFile[] files, HttpServletRequest request)
            throws RemoteException, IllegalArgumentException, IOException {
        IFileNode[] fileNodes = new IFileNode[files.length];
        for (int i = 0; i < files.length && i < fileNodes.length; i++) {
            fileNodes[i] = newProjectContent(projectId, files[i], request);
        }
        return fileNodes;
    }


     // Aggregate root
     @GetMapping("/project/{projectId}")
     IFileNode allFilesFromProject(@PathVariable(name = "projectId") String projectId, @RequestParam(value = "content", defaultValue = "false") Boolean showFileContents) throws RemoteException, IllegalStateException {
        return fileService.getAllFileNodesFromProject(projectId, showFileContents);//TODO: if result null then not found.
     }
 
    
 
     // Single item
     @GetMapping("/project/{projectId}/**")
     IFileNode oneFileInProject(@PathVariable(name = "projectId") String projectId, @RequestParam(value = "content", defaultValue = "false") Boolean showContent, HttpServletRequest request) throws RemoteException, IllegalStateException {

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
 
     @DeleteMapping("/project/{projectId}/**")
     void deleteFileFromProject(@PathVariable(name = "projectId") String projectId, HttpServletRequest request) throws RemoteException {
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        fileService.deleteFileNode(path);
         
     }

}