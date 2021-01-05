package edu.kit.palladio.remote.dataprocessinganalysis;


import java.io.Serializable;
import java.util.Map;

public interface ILaunchConfig extends Serializable {
	String getLaunchName();
	String getUsageModelPath();
	String getAllocModelPath();
	String getCharacteristicsModelPath();
	String getProverFactoryId();
	String getAnalysisGoalId();
	LaunchFlags[] getLaunchFlags();
	Map<String, String> getParameters();
	
}
