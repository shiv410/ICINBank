package com.icin.response;

public class ChequeResponse {
	
	private boolean status;
	   private String responseMessage;
	   private long account;

	   public ChequeResponse() {
	   }

	   public boolean isStatus() {
	      return this.status;
	   }

	   public void setStatus(boolean status) {
	      this.status = status;
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
