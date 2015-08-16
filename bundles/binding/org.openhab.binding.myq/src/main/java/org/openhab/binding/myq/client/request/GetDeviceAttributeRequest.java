package org.openhab.binding.myq.client.request;

public class GetDeviceAttributeRequest extends MyQRequest {
	private String deviceId;
	private String name;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
