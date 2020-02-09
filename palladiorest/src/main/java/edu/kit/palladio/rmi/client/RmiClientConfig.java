package edu.kit.palladio.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import edu.kit.palladio.rest.filemanagment.FileService;
import edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;
import edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI;

@Configuration
public class RmiClientConfig implements IRmiClientConfig {

    @Override
    @Bean
    public RmiProxyFactoryBean getProjectManagementRmiProxy() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IProjectManager.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:10099/" + IProjectManager.class.getName());
        return rmiProxyFactoryBean;
    }

    @Override
    @Bean
    public RmiProxyFactoryBean getIRemoteFileUploadProxy() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IRemoteFileUpload.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:10099/" + IRemoteFileUpload.class.getName());
        return rmiProxyFactoryBean;
    }


    @Override
    @Bean
    public RmiProxyFactoryBean getIProverManagerProxy(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IProverManagerRMI.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:10099/" + IProverManagerRMI.class.getName());
        return rmiProxyFactoryBean;
    }

    @Override
    @Bean
    public FileService getFileService() {
        return new FileService((IRemoteFileUpload)this.getIRemoteFileUploadProxy().getObject());
    }


    

    
}