package helloclient.config;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import helloclient.domain.HelloWorldServiceInfo;

import java.util.Map;

public class HelloWorldServiceInfoCreator extends CloudFoundryServiceInfoCreator<HelloWorldServiceInfo> {
    public HelloWorldServiceInfoCreator() {
        super(new Tags());
    }

    @Override
    public HelloWorldServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");

        String id = (String) serviceData.get("name");
        String uri = (String) credentials.get("uri");

        return new HelloWorldServiceInfo(id, uri);
    }

    @Override
    public boolean accept(Map<String, Object> serviceData) {
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");
        String uri = (String) credentials.get("uri");
        return uri != null;
    }
}
