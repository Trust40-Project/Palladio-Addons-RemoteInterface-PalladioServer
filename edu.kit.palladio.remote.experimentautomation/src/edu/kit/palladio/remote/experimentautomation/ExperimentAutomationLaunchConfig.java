package edu.kit.palladio.remote.experimentautomation;

public class ExperimentAutomationLaunchConfig implements IExperimentAutomationLaunchConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092557724548288380L;
	private String launchName;
	private String experimentsLocationPath;
	private String[] filteredExperimentIds;
	
	public ExperimentAutomationLaunchConfig() {
		
	}
	public ExperimentAutomationLaunchConfig(String launchName, String experimentsLocationPath,
			String[] filteredExperimentIds) {
		this.launchName = launchName;
		this.experimentsLocationPath = experimentsLocationPath;
		this.filteredExperimentIds = filteredExperimentIds;
	}

	@Override
	public String getLaunchName() {
		return this.launchName;
	}

	@Override
	public String getExperimentsLocationPath() {
		return this.experimentsLocationPath;
	}

	@Override
	public String[] getFilteredExperimentIds() {
		return this.filteredExperimentIds;
	}

}
