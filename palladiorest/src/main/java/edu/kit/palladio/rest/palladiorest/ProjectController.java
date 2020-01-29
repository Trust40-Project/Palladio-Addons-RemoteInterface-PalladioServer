package edu.kit.palladio.rest.palladiorest;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
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

    public ProjectController(@Autowired FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/project/{projectId}/**")
    IFileNode newProjectContent(@PathVariable(name = "projectId") String projectId,
            @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
            throws RemoteException, IOException, IllegalArgumentException{
        final String requestPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
                .toString();

        String path = projectId + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, requestPath);
        
        return fileService.save(path, file);
    }

}