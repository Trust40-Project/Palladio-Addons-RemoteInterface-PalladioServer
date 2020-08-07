package edu.kit.palladio.rest.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * IRmiClientFactory
 */
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import edu.kit.palladio.rcp.api.ISolutionManagerRemote;
import edu.kit.palladio.rest.services.FileService;

@Configuration
public interface IRmiClientConfig {

    @Bean
    RmiProxyFactoryBean getProjectManagementRmiProxy();

    @Bean
    RmiProxyFactoryBean getIRemoteFileUploadProxy();

    @Bean
    FileService getFileService();

    @Bean
    RmiProxyFactoryBean getIProverManagerProxy();

    @Bean
    RmiProxyFactoryBean getIQueryManagerProxy();

    @Bean
    RmiProxyFactoryBean getIAnalysisLauncherProxy();

    @Bean
    RmiProxyFactoryBean getISolutionManagerRemoteProxy();

}