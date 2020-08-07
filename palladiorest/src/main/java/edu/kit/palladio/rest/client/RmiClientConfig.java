package edu.kit.palladio.rest.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import edu.kit.palladio.rcp.api.ISolutionManagerRemote;
import edu.kit.palladio.rest.services.FileService;
import edu.kit.palladio.rmi.dataprocessinganalysis.IAnalysisLauncher;
import edu.kit.palladio.rmi.filemanagment.IRemoteFileUpload;
import edu.kit.palladio.rmi.projectmanagment.IProjectManager;
import edu.kit.palladio.rmi.querymanagement.IQueryManagerRMI;
import edu.kit.palladio.rmi.supportingprolog4j.IProverManagerRMI;

@Configuration
public class RmiClientConfig implements IRmiClientConfig {
    private final static int PORT = 10999;

    @Override
    @Bean
    public RmiProxyFactoryBean getProjectManagementRmiProxy() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IProjectManager.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + IProjectManager.class.getName());
        return rmiProxyFactoryBean;
    }

    @Override
    @Bean
    public RmiProxyFactoryBean getIRemoteFileUploadProxy() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IRemoteFileUpload.class);
        System.out.println(IRemoteFileUpload.class.getName());
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + IRemoteFileUpload.class.getName());
        return rmiProxyFactoryBean;
    }


    @Override
    @Bean
    public RmiProxyFactoryBean getIProverManagerProxy(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IProverManagerRMI.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + IProverManagerRMI.class.getName());
        return rmiProxyFactoryBean;
    }

    @Override
    @Bean
    public RmiProxyFactoryBean getIQueryManagerProxy(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IQueryManagerRMI.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + IQueryManagerRMI.class.getName());
        return rmiProxyFactoryBean;
    }

    @Override
    @Bean
    public RmiProxyFactoryBean getIAnalysisLauncherProxy(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IAnalysisLauncher.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + IAnalysisLauncher.class.getName());
        return rmiProxyFactoryBean;
    }

    

    @Override
    @Bean
    public FileService getFileService() {
        return new FileService((IRemoteFileUpload)this.getIRemoteFileUploadProxy().getObject());
    }

    
    @Override
    @Bean
    public RmiProxyFactoryBean getISolutionManagerRemoteProxy() {

        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ISolutionManagerRemote.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:"+PORT+"/" + ISolutionManagerRemote.class.getName());
        return rmiProxyFactoryBean;
    }

    

    
}