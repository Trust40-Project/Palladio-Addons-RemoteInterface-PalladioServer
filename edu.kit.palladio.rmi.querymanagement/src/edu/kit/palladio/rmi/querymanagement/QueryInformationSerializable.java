package edu.kit.palladio.rmi.querymanagement;

import java.io.Serializable;

public class QueryInformationSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String name;
	private final String id;
	
	public QueryInformationSerializable(String id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
