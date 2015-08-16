package org.openhab.binding.myq.garagedoor;

public enum DoorState {
	CLOSED("0"),
	OPEN("1"),
	STOPPED("3"),
	OPENING("4"),
	CLOSING("5");
	
	private String state;
	
	DoorState(String state){
		this.state = state;
	}
	
	public String getStateCode() { return state; }
}
