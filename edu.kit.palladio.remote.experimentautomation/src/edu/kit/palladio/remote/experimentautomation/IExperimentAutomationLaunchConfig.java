package edu.kit.palladio.remote.experimentautomation;

import java.io.Serializable;

public interface IExperimentAutomationLaunchConfig extends Serializable {
	String getLaunchName();
	String getExperimentsLocationPath();
	String[] getFilteredExperimentIds();
}	
