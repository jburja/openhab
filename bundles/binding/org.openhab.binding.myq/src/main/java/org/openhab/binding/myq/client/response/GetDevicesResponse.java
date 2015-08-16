package org.openhab.binding.myq.client.response;

import java.util.ArrayList;
import java.util.List;

import org.openhab.binding.myq.garagedoor.Device;

public class GetDevicesResponse extends PostLoginResponse {

	List<Device> devices = new ArrayList<Device>();
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
}
