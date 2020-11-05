package edu.kit.palladio.remote.analysis.api;

import java.util.Map;

public interface IAnalysisInput {
	public String getTitel();
	public String getDescription();
	public Type getType();
	public Map<String, IProperty> getProperties();
	public String[] required();
}
