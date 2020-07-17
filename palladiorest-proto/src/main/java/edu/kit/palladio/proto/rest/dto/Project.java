package edu.kit.palladio.proto.rest.dto;

import java.rmi.RemoteException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Project implements IProject {
	/**
	 * 
	 */
	private final String projectId;
	
	@JsonCreator
	public Project(@JsonProperty("projectId") String projectId){
		this.projectId = projectId;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

}
