package org.openhab.binding.myq.client.response;

import com.google.gson.annotations.Expose;


public class PostLoginResponse extends MyQResponse {
	@Expose
	String correlationId;
	
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
