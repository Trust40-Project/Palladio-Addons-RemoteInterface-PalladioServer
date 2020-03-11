package edu.kit.palladio.rmi.querymanagement;

import java.io.Serializable;
import java.util.Map;

import org.palladiosimulator.pcm.dataprocessing.analysis.executor.workflow.query.QueryParameterType;

public class QueryInformationSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String name;
	private final String id;
	private final Map<String, QueryParameterType> requiredParameters;
	
	
	public QueryInformationSerializable(String id, String name, Map<String, QueryParameterType> requiredParameters) {
		this.name = name;
		this.id = id;
		this.requiredParameters = requiredParameters;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Map<String, QueryParameterType> getRequiredParameters() {
		return requiredParameters;
	}
}
