package helloclient.domain;

import org.springframework.cloud.service.BaseServiceInfo;

public class HelloWorldServiceInfo extends BaseServiceInfo {
    private final String uri;

    public HelloWorldServiceInfo(String id, String uri) {
        super(id);
        this.uri = uri;
    }

    @ServiceProperty
    public String getUri() {
        return uri;
    }

	@Override
	public String toString() {
		return "HelloWorldServiceInfo [uri=" + uri + ", id=" + id + "]";
	}
    
    
}
