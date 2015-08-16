package org.openhab.binding.myq.internal;


public class MyQConnectionProperties {
	private String hostname;
	private String appId;
	private String culture;
	private String username;
	private String password; 
	private String securityToken;
	
	public MyQConnectionProperties() {
		this.hostname = "myqexternal.myqdevice.com";
		this.appId = "Vj8pQggXLhLy0WHahglCD4N1nAkkXQtGYpq2HrHD7H1nvmbT55KqtN6RSF4ILB%2fi";
		this.culture = "en";  // TODO: find if there's other cultures and make configurable
		this.securityToken = "null";
	}
	
	public MyQConnectionProperties(String username, String password) {
		this.hostname = "myqexternal.myqdevice.com";
		this.appId = "Vj8pQggXLhLy0WHahglCD4N1nAkkXQtGYpq2HrHD7H1nvmbT55KqtN6RSF4ILB%2fi";
		this.culture = "en";  // TODO: find if there's other cultures and make configurable
		this.username = username;
		this.password = password;
		this.securityToken = "null";
	}
	
	public MyQConnectionProperties(String hostname, String username, String password) {
		this.hostname = hostname;
		this.appId = "Vj8pQggXLhLy0WHahglCD4N1nAkkXQtGYpq2HrHD7H1nvmbT55KqtN6RSF4ILB%2fi";
		this.culture = "en";  // TODO: find if there's other cultures and make configurable
		this.username = username;
		this.password = password;
		this.securityToken = "null";
	}
	
	public MyQConnectionProperties(String hostname, String username, String password, String culture) {
		this.hostname = hostname;
		this.appId = "Vj8pQggXLhLy0WHahglCD4N1nAkkXQtGYpq2HrHD7H1nvmbT55KqtN6RSF4ILB%2fi";
		this.culture = culture; 
		this.username = username;
		this.password = password;
		this.securityToken = "null";
	}
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

}
