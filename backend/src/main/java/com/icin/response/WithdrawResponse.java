package com.icin.response;

public class WithdrawResponse {

	private boolean withdrawStatus;
	private String responseMessage;
	private long account;

	public WithdrawResponse() {
	}

	public boolean isWithdrawStatus() {
		return this.withdrawStatus;
	}

	public void setWithdrawStatus(boolean withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public long getAccount() {
		return this.account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

}
