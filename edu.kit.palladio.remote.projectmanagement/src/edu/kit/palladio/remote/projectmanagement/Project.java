package edu.kit.palladio.remote.projectmanagement;


public class Project implements IProject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812722787414546751L;
	private String projectId;
	
	public Project(String projectId){
		this.projectId = projectId;
	}
	
	public Project(){
		
	}


	@Override
	public String getProjectId() {
		return projectId;
	}

}
