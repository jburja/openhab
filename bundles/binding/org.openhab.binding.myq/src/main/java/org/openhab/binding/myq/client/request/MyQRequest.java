package org.openhab.binding.myq.client.request;

public class MyQRequest {
	  private String applicationId;
	  private String securityToken;

	  public String getApplicationId()
	  {
	    return this.applicationId;
	  }
	  public void setApplicationId(String applicationId) {
	    this.applicationId = applicationId;
	  }
	  public String getSecurityToken() {
	    return this.securityToken;
	  }
	  public void setSecurityToken(String securityToken) {
	    this.securityToken = securityToken;
	  }
}
