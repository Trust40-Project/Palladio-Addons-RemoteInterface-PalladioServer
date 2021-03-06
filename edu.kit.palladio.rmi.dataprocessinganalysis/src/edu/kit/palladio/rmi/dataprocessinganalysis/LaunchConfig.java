package edu.kit.palladio.rmi.dataprocessinganalysis;

import java.util.Map;

public class LaunchConfig implements ILaunchConfig {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String usageModelPath;
    private String allocModelPath;
    private String characteristicsModelPath;
    private String proverFactoryId;
    private String analysisGoalId;
    private LaunchFlags[] launchFlags;
    private String launchName;
    private Map<String, String> parameters;


    
    public LaunchConfig(String usageModelPath,
                        String allocModelPath,
                        String characteristicsModelPath,
                        String proverFactoryId,
                        String analysisGoalId,
                        LaunchFlags[] launchFlags,
                        String launchName,
                        Map<String, String> parameters

    ){
        this.usageModelPath = usageModelPath;
        this.allocModelPath = allocModelPath;
        this.proverFactoryId = proverFactoryId;
        this.analysisGoalId = analysisGoalId;
        this.launchFlags = launchFlags;
        this.launchName = launchName;
        this.parameters = parameters;
    }
    
    @Override
    public String getUsageModelPath() {
        return usageModelPath;
    }

    @Override
    public String getAllocModelPath() {
        return allocModelPath;
    }

    @Override
    public String getCharacteristicsModelPath() {
        return characteristicsModelPath;
    }

    @Override
    public String getProverFactoryId() {
        return proverFactoryId;
    }

    @Override
    public String getAnalysisGoalId() {
        return analysisGoalId;
    }

    @Override
    public LaunchFlags[] getLaunchFlags() {
        return launchFlags;
    }

	@Override
	public String getLaunchName() {
		return launchName;
	}

	@Override
	public Map<String, String> getParameters() {
		return parameters;
	}

    
}