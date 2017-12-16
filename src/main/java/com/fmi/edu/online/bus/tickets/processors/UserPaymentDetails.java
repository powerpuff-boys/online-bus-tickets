package com.fmi.edu.online.bus.tickets.processors;

import java.util.Base64;

public class UserPaymentDetails {
	private String userName;
	private String password;

	public UserPaymentDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public static UserPaymentDetailsParser getUserDetailsParser(){
		return new UserPaymentDetails.UserPaymentDetailsParser();
	}

	@Override
	public String toString() {
		return "UserPaymentDetails [userName=" + userName + ", password=" + password + "]";
	}

	public static class UserPaymentDetailsParser {
		public UserPaymentDetails parse(String userDetailsEncoded){
			byte[] userData = Base64.getDecoder().decode(userDetailsEncoded);
			String userDataString = new String(userData);
			String[] userDataSplitted = userDataString.split(":");
			return new UserPaymentDetails(userDataSplitted[0], userDataSplitted[1]);
		}
	}
	
}
