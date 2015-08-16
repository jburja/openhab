package org.openhab.binding.myq.client.request;

public class LoginRequest extends MyQRequest {

	private String username;
	private String password;
	private String culture;
	
	public LoginRequest() {
		System.out.println("Initializing loginrequest");
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
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}	
	
}
