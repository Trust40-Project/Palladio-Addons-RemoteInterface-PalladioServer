package edu.kit.palladio.proto.rest.dto;

import java.util.Iterator;
import java.util.List;

import edu.kit.palladio.proto.projectmanagement.CreateProjectResponse;
import edu.kit.palladio.proto.projectmanagement.DeleteProjectResponse;
import edu.kit.palladio.proto.projectmanagement.Project;
import io.grpc.StatusRuntimeException;

public interface IProjectManager {
	//post complete project with description and natures
	CreateProjectResponse createProject(final IProject projectToCreate) throws StatusRuntimeException;
	DeleteProjectResponse deleteProject(final String projectId) throws StatusRuntimeException;
	Project getProject(final String projectId)throws StatusRuntimeException;
	Iterator<Project> getProjects()throws StatusRuntimeException;
}
