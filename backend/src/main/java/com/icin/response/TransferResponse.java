package com.icin.response;

public class TransferResponse {
	
	private boolean transferStatus;
	   private String responseMessage;
	   private long saccount;

	   public TransferResponse() {
	   }

	   public boolean isTransferStatus() {
	      return this.transferStatus;
	   }

	   public void setTransferStatus(boolean transferStatus) {
	      this.transferStatus = transferStatus;
	   }

	   public String getResponseMessage() {
	      return this.responseMessage;
	   }

	   public void setResponseMessage(String responseMessage) {
	      this.responseMessage = responseMessage;
	   }

	   public long getSaccount() {
	      return this.saccount;
	   }

	   public void setSaccount(long saccount) {
	      this.saccount = saccount;
	   }


}
