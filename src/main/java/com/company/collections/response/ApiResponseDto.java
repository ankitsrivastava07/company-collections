package com.company.collections.response;

public class ApiResponseDto {
	
	private String msg;
	private Object data;
	private Object error;
	private Boolean status;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean flag) {
		this.status = flag;
	}

}
