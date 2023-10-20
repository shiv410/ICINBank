package com.icin.response;

public class RegisterResponse {

	private boolean registrationStatus;
	   private String responseMessage;
	   private String username;

	   public RegisterResponse() {
	   }

	   public boolean getRegistrationStatus() {
	      return this.registrationStatus;
	   }

	   public void setRegistrationStatus(boolean registrationStatus) {
	      this.registrationStatus = registrationStatus;
	   }

	   public String getResponseMessage() {
	      return this.responseMessage;
	   }

	   public void setResponseMessage(String responseMessage) {
	      this.responseMessage = responseMessage;
	   }

	   public String getUsername() {
	      return this.username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

}
