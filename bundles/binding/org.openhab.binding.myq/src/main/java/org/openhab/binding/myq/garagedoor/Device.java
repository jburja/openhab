package org.openhab.binding.myq.garagedoor;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Device {

	private int myQDeviceId;
	private int myQDeviceTypeId;
	private String myQDeviceTypeName;
	private String deviceId;
	private String deviceName;
	private int typeId;
	private String typeName;
	private String registrationDateTime;
	private String serialNumber;
	private String userName;
	private int userConnectServerId;
	private int userCountryId;
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private String childrenMyQDeviceIds;
	private String updatedBy;
	private String updatedDate;
	private int parentMyQDeviceId;
	
	public int getMyQDeviceId() {
		return myQDeviceId;
	}
	public void setMyQDeviceId(int myQDeviceId) {
		this.myQDeviceId = myQDeviceId;
	}
	public int getMyQDeviceTypeId() {
		return myQDeviceTypeId;
	}
	public void setMyQDeviceTypeId(int myQDeviceTypeId) {
		this.myQDeviceTypeId = myQDeviceTypeId;
	}
	public String getMyQDeviceTypeName() {
		return myQDeviceTypeName;
	}
	public void setMyQDeviceTypeName(String myQDeviceTypeName) {
		this.myQDeviceTypeName = myQDeviceTypeName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(String registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserConnectServerId() {
		return userConnectServerId;
	}
	public void setUserConnectServerId(int userConnectServerId) {
		this.userConnectServerId = userConnectServerId;
	}
	public int getUserCountryId() {
		return userCountryId;
	}
	public void setUserCountryId(int userCountryId) {
		this.userCountryId = userCountryId;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public String getChildrenMyQDeviceIds() {
		return childrenMyQDeviceIds;
	}
	public void setChildrenMyQDeviceIds(String childrenMyQDeviceIds) {
		this.childrenMyQDeviceIds = childrenMyQDeviceIds;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getParentMyQDeviceId() {
		return parentMyQDeviceId;
	}
	public void setParentMyQDeviceId(int parentMyQDeviceId) {
		this.parentMyQDeviceId = parentMyQDeviceId;
	}
}
