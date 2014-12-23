package helloclient.config;

import java.util.List;

import helloclient.domain.HelloWorldServiceInfo;
import helloclient.domain.SystemConfiguration;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class Config {
    @Configuration
    @Profile("cloud")
    static class CloudConfiguration {
        @Bean
        Cloud cloud() {
            return new CloudFactory().getCloud();
        }

        @Bean
        HelloWorldServiceInfo helloWorldServiceInfo() {
            List<ServiceInfo> serviceInfos = cloud().getServiceInfos();
            for (ServiceInfo serviceInfo : serviceInfos) {
            	if (serviceInfo instanceof HelloWorldServiceInfo) {
                    return (HelloWorldServiceInfo) serviceInfo;
                }
            }
            return null;
        }

    	
	    @Bean
	    public SystemConfiguration systemConfiguration() {
	    	HelloWorldServiceInfo hwsi = helloWorldServiceInfo();
	    	System.out.println("CloudConfiguration.systemConfiguration - Leveraging endpoint " + hwsi);
	    	if(hwsi == null) {
		    	return new SystemConfiguration(null);
	    	} else {
		    	return new SystemConfiguration(hwsi.getUri());
	    	}
        }
    }

    @Configuration
    @Profile("default")
    static class LocalConfiguration {
    	@Bean
	     public SystemConfiguration systemConfiguration() {
	     	return new SystemConfiguration("local - not yet supported");
	     }
     }

}
