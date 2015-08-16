package org.openhab.binding.myq.client.response;

public class SetDeviceAttributeResponse extends PostLoginResponse {
	private String updatedTime;

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
