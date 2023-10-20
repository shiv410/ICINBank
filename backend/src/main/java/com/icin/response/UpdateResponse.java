package com.icin.response;

public class UpdateResponse {

	public boolean flag;
	public String message;

	public UpdateResponse() {
	}

	public boolean isFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
