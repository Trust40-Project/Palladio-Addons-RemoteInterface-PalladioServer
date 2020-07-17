package edu.kit.palladio.proto.rest.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kit.palladio.proto.projectmanagement.CreateProjectResponse;
import edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse;
import edu.kit.palladio.proto.projectmanagement.GetProjectsRequest;
import edu.kit.palladio.proto.projectmanagement.Project;
import edu.kit.palladio.proto.projectmanagement.ProjectManagerGrpc;
import edu.kit.palladio.proto.projectmanagement.ProjectManagerGrpc.ProjectManagerBlockingStub;
import edu.kit.palladio.proto.rest.ChannelFactory;
import edu.kit.palladio.proto.rest.dto.IProject;
import edu.kit.palladio.proto.rest.dto.IProjectManager;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;

@Service
public class ProjectmanagementClient implements IProjectManager {
    private final ProjectManagerBlockingStub blockingStub;

    /**
     * Construct client for accessing Projectmanagement server using the existing
     * channel.
     */
    @Autowired
    public ProjectmanagementClient(final ChannelFactory channelFactory) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's
        // responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to
        // reuse Channels.
        blockingStub = ProjectManagerGrpc.newBlockingStub(channelFactory.getChannel());

    }

    public CreateProjectResponse createProject(final IProject projectToCreate) throws StatusRuntimeException {
        final Project project = Project.newBuilder().setProjectId(projectToCreate.getProjectId()).build();
        CreateProjectResponse response;
        
        response = blockingStub.createProject(project);
        
        return response;
    }

    @Override
    public DeleteProjectResponse deleteProject(String projectId)throws StatusRuntimeException {
        final Project project = Project.newBuilder().setProjectId(projectId).build();


       DeleteProjectResponse response = blockingStub.deleteProject(project);
        return response;
    }

    @Override
    public Project getProject(String projectId)throws StatusRuntimeException {
        final Project request = Project.newBuilder().setProjectId(projectId).build();
        
        Project response = blockingStub.getProject(request);
        return response;
    }

    @Override
    public Iterator<Project> getProjects()throws StatusRuntimeException {
        // TODO Auto-generated method stub
        final GetProjectsRequest request = GetProjectsRequest.getDefaultInstance();
        Iterator<Project> projects = blockingStub.getProjects(request);
        return projects;
    }
}