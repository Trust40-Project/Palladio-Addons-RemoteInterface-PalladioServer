package edu.kit.palladio.rmi.dataprocessinganalysis;

public interface ILaunchConfig {
	String getUsageModelPath();
	String getAllocModelPath();
	String getCharacteristicsModelPath();
	String getProverFactoryId();
	String getAnalysisGoalId();
	LaunchFlags[] getLaunchFlags();
	
}
