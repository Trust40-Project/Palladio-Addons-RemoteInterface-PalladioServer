package edu.kit.palladio.rmi.supportingprolog4j;

import java.io.Serializable;

public class ProverInformationSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String name;
	private final boolean needsNativeExecutables;

	public ProverInformationSerializable(String id, String name, boolean needsNativeExecutables) {
		this.id = id;
		this.name = name;
		this.needsNativeExecutables = needsNativeExecutables;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isNeedsNativeExecutables() {
		return needsNativeExecutables;
	}

}
