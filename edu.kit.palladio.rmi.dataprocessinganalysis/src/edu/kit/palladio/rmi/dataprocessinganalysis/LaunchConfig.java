package edu.kit.palladio.rmi.dataprocessinganalysis;

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


    
    public LaunchConfig(String usageModelPath,
                        String allocModelPath,
                        String characteristicsModelPath,
                        String proverFactoryId,
                        String analysisGoalId,
                        LaunchFlags[] launchFlags

    ){
        this.usageModelPath = usageModelPath;
        this.allocModelPath = allocModelPath;
        this.proverFactoryId = proverFactoryId;
        this.analysisGoalId = analysisGoalId;
        this.launchFlags = launchFlags;
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

    
}