package edu.kit.palladio.remote.experimentautomation;


public interface IExperimentAutomationLauncher {
	String launch(final IExperimentAutomationLaunchConfig launchConfig) throws IllegalArgumentException;
}
