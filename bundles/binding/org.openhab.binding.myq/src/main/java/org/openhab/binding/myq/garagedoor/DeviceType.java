package org.openhab.binding.myq.garagedoor;

public enum DeviceType {
	GATEWAY("Gateway"),
	GARAGE_DOOR_OPENER("GarageDoorOpener");
	
	private String type;
	
	DeviceType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
