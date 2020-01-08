package edu.kit.palladio.rmi.projectmanagment;

public class Project implements IProject {
	private final String projectId;
	
	Project(String projectId){
		this.projectId = projectId;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

}
