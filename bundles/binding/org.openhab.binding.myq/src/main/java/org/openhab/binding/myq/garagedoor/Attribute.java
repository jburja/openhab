package org.openhab.binding.myq.garagedoor;

import com.google.gson.annotations.Expose;

public class Attribute {
	public enum AttributeNames {
		ENUMERATE_REQUIRED("enumeraterrequired"),
		LEARN_MODE_STATE("learnmodestate"),
		ADDED_TIME("addedtime"),
		DEVICES("devices"),
		NUMBER_OF_DEVICES("numdevices"),
		DESCRIPTION("desc"),
		FIRMWARE_VERSION("fwver"),
		NAME("name"),
		DEVICE_NAME("deviceName"),
		CUSTOMER_NAME("customerName");
		
		String attributeName;
		AttributeNames(String name) {
			this.attributeName = name;
		}
	}
	
	private int attributeId;
	private String name;
	private String value;
	private String updatedTime;
	private int myQDeviceTypeAttributeId;
	private boolean isDeviceProperty;
	private String attributeDisplayName;
	private boolean isPersistent;
	private boolean isTimeSeries;
	private boolean isGlobal;
	private String updatedDate;
	
	public int getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public int getMyQDeviceTypeAttributeId() {
		return myQDeviceTypeAttributeId;
	}
	public void setMyQDeviceTypeAttributeId(int myQDeviceTypeAttributeId) {
		this.myQDeviceTypeAttributeId = myQDeviceTypeAttributeId;
	}
	public boolean isDeviceProperty() {
		return isDeviceProperty;
	}
	public void setDeviceProperty(boolean isDeviceProperty) {
		this.isDeviceProperty = isDeviceProperty;
	}
	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}
	public void setAttributeDisplayName(String attributeDisplayName) {
		this.attributeDisplayName = attributeDisplayName;
	}
	public boolean isPersistent() {
		return isPersistent;
	}
	public void setPersistent(boolean isPersistent) {
		this.isPersistent = isPersistent;
	}
	public boolean isTimeSeries() {
		return isTimeSeries;
	}
	public void setTimeSeries(boolean isTimeSeries) {
		this.isTimeSeries = isTimeSeries;
	}
	public boolean isGlobal() {
		return isGlobal;
	}
	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}