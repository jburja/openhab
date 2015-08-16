package org.openhab.binding.myq.client.response;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDeviceAttributeResponse extends PostLoginResponse {
	@Expose
	private String attributeValue;
	@Expose
	@SerializedName("UpdatedTime")
	private String updatedTimeInMilli;
	private String updatedTime;
	
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getUpdatedTimeInMilli() {
		return updatedTimeInMilli;
	}
	public void setUpdatedTimeInMilli(String updatedTimeInMilli) {
		this.updatedTimeInMilli = updatedTimeInMilli;
	}
	public String getUpdatedTime() {
		//TODO clean up
		Date date = new Date(Long.parseLong(updatedTimeInMilli));
		return(String.format("%02d:%02d:%02d", 
				date.getHours(),
				date.getMinutes(),
				date.getSeconds()));   
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTimeInMilli = updatedTime;
	}
}
