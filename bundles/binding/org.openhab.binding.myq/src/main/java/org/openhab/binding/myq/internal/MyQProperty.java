package org.openhab.binding.myq.internal;

import org.apache.commons.lang.StringUtils;

public enum MyQProperty {
	STATUS("STATUS");
	
	private String code;
	
	private MyQProperty(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static MyQProperty fromString(String command) {
		if (!StringUtils.isEmpty(command)) {
			for (MyQProperty commandType : MyQProperty.values()) {
				if (commandType.getCode().equals(command)) {
					return commandType;
				}
			}
		}
		
		throw new IllegalArgumentException("Invalid command: " + command);
	}
}
