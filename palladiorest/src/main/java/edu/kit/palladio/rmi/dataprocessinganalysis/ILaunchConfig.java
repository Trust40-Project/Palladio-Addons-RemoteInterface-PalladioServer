package edu.kit.palladio.rmi.dataprocessinganalysis;

import java.io.Serializable;

public interface ILaunchConfig extends Serializable{
	String getUsageModelPath();
	String getAllocModelPath();
	String getCharacteristicsModelPath();
	String getProverFactoryId();
	String getAnalysisGoalId();
	LaunchFlags[] getLaunchFlags();
	
}
