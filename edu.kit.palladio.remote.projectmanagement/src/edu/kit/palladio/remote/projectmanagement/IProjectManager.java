package edu.kit.palladio.remote.projectmanagement;
import java.io.Serializable;
import java.util.List;

public interface IProjectManager extends Serializable {
	//post complete project with description and natures
	IProject createProject(final IProject projectToCreate) throws  Throwable, IllegalStateException;
	IProject createProject(final String projectId) throws  Throwable, IllegalStateException;
	void deleteProject(final String projectId) throws  IllegalStateException;
	void deleteProject(final IProject toDelete) throws  IllegalStateException;
	IProject getProject(final String projectId) throws  IllegalStateException, IllegalArgumentException;
	List<IProject> getProjects();
	void setNatures(final IProject projectToSetNatures, final String[] natures) throws IllegalStateException, IllegalArgumentException;
	void close() throws  IllegalStateException;
}
