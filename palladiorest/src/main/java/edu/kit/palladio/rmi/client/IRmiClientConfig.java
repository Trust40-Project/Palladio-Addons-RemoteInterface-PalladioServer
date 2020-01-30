package edu.kit.palladio.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * IRmiClientFactory
 */
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import edu.kit.palladio.rest.filemanagment.FileService;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;

@Configuration
public interface IRmiClientConfig {

    @Bean
    RmiProxyFactoryBean getProjectManagementRmiProxy();

    @Bean
    RmiProxyFactoryBean getIRemoteFileUploadProxy();

    @Bean
    FileService getFileService();

}