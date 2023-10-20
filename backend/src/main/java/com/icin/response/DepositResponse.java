package com.icin.response;

public class DepositResponse {
	
	 private boolean depositStatus;
	   private String responseMessage;
	   private long account;

	   public DepositResponse() {
	   }

	   public boolean isDepositStatus() {
	      return this.depositStatus;
	   }

	   public void setDepositStatus(boolean depositStatus) {
	      this.depositStatus = depositStatus;
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
