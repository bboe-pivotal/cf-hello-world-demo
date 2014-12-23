package helloclient.domain;

public class SystemConfiguration {
	private String remoteURI;

	public SystemConfiguration(String remoteURI) {
		this.remoteURI = remoteURI;
	}
	
	public String getRemoteURI() {
		return remoteURI;
	}
	
	public String toString() {
		return "SystemConfiguration(remoteURI: "+remoteURI+")";
	}
	
}
