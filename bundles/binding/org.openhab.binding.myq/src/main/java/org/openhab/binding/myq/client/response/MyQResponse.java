package org.openhab.binding.myq.client.response;

import com.google.gson.annotations.Expose;

public class MyQResponse {
	
	@Expose
	String returnCode;
	@Expose
	String errorMessage;

	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
