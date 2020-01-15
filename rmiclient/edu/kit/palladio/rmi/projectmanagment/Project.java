package edu.kit.palladio.rmi.projectmanagment;

import java.rmi.RemoteException;

public class Project implements IProject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812722787414546751L;
	private final String projectId;
	
	public Project(String projectId){
		this.projectId = projectId;
	}

	@Override
	public String getProjectId() throws RemoteException {
		return projectId;
	}

}
