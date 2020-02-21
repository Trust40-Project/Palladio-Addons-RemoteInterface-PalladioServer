package edu.kit.palladio.rmi.dataprocessinganalysis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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


    //TODO: Add default values.
    @JsonCreator
    public LaunchConfig(@JsonProperty("usageModelPath") String usageModelPath,
                        @JsonProperty("allocModelPath") String allocModelPath,
                        @JsonProperty("characteristicsModelPath") String characteristicsModelPath,
                        @JsonProperty("proverFactoryId") String proverFactoryId,
                        @JsonProperty("analysisGoalId") String analysisGoalId,
                        @JsonProperty("launchFlags") LaunchFlags[] launchFlags,
                        @JsonProperty("launchName") String launchName

    ){
        this.usageModelPath = usageModelPath;
        this.allocModelPath = allocModelPath;
        this.proverFactoryId = proverFactoryId;
        this.analysisGoalId = analysisGoalId;
        this.launchFlags = launchFlags;
        this.characteristicsModelPath = characteristicsModelPath;
        this.launchName = launchName;
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
    
}