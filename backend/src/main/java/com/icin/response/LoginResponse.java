package com.icin.response;

public class LoginResponse {

	private boolean loginStatus;
	   private String responseMessage;
	   private String username;

	   public LoginResponse() {
	   }

	   public boolean getLoginStatus() {
	      return this.loginStatus;
	   }

	   public void setLoginStatus(boolean loginStatus) {
	      this.loginStatus = loginStatus;
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
