package edu.kit.palladio.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import edu.kit.palladio.rmi.projectmanagment.IProjectManager;

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
    public RmiProxyFactoryBean getFileManagementRmiProxy() {
        // TODO Auto-generated method stub
        return null;
    }

    
}