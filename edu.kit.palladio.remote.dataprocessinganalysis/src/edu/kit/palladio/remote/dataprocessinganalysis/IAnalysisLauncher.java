package edu.kit.palladio.remote.dataprocessinganalysis;



public interface IAnalysisLauncher {
	String launch(final ILaunchConfig launchConfig) throws IllegalArgumentException;
}
