package com.whoiszxl.blc.common;

/**
 * 
 * @author whoiszxl
 *
 */
public enum ResponseCode {
	
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"ERROR"),
	ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
	SYSTEM_ERROR(4,"SYSTEM_ERROR"),
	
	
	LOGIN_AUTH_FAIL(401, "LOGIN_AUTH_FAIL");
	
	private final int code;
	
	private final String desc;

	ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
}