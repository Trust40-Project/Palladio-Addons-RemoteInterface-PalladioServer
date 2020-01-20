package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.RemoteException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Project implements IProject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812722787414546751L;
	private final String projectId;
	
	@JsonCreator
	public Project(@JsonProperty("projectId") String projectId){
		this.projectId = projectId;
	}

	@Override
	public String getProjectId() throws RemoteException {
		return projectId;
	}

}
