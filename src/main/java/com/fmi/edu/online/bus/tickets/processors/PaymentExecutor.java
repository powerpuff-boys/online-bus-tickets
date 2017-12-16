package com.fmi.edu.online.bus.tickets.processors;

import java.util.ArrayList;
import java.util.List;

public class PaymentExecutor {

	private static final double DEFAULT_TICKET_PRICE = 1.6;
	private static List<UserPaymentInformation> userData = new ArrayList<UserPaymentInformation>() {
		private static final long serialVersionUID = 1L;

		{
			add(new UserPaymentInformation("encho.belezirev@sap.com", "fmi-codes", 120.5));
			add(new UserPaymentInformation("nikola.popov@sap.com", "fmi-codes", 1.2));
			add(new UserPaymentInformation("gesh.geshov@sap.com", "fmi-codes", 200));
			add(new UserPaymentInformation("pesho.peshev@sap.com", "fmi-codes", 0.5));
			add(new UserPaymentInformation("niki.nikov@sap.com", "fmi-codes", 0));
		}
	};

	public AuthorizationCheck authorizeUser(String userName, String password) {
		if (!contains(userName)) {
			return AuthorizationCheck.BAD_CREDENTIALS;
		}

		String userPassword = retrieveUser(userName).getPassword();
		if (userPassword.equalsIgnoreCase(password)) {
			return AuthorizationCheck.SUCCESSFUL;
		}

		return AuthorizationCheck.BAD_CREDENTIALS;
	}

	private boolean contains(String userName) {
		UserPaymentInformation info = retrieveUser(userName);
		return info != null;
	}

	private UserPaymentInformation retrieveUser(String userName) {
		UserPaymentInformation info = userData.stream().filter(data -> data.getUserName().equals(userName)).findFirst()
				.orElse(null);
		return info;
	}

	public boolean ensurePayment(String userName, String password) {
		UserPaymentInformation info = retrieveUser(userName);
		return info.getAmount() >= DEFAULT_TICKET_PRICE;
	}

	
	public boolean executePayment(String userName, String password) {
		UserPaymentInformation info = retrieveUser(userName);
		if (info.getAmount() < DEFAULT_TICKET_PRICE) {
			return false;
		}
		info.setAmount(info.getAmount() - DEFAULT_TICKET_PRICE);
		return true;
	}

	private static class UserPaymentInformation {
		private String userName;
		private String password;
		private double amount;

		public UserPaymentInformation(String userName, String password, double amount) {
			this.userName = userName;
			this.password = password;
			this.amount = amount;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}

		public double getAmount() {
			return amount;
		}
		
		public void setAmount(double newAmount){
			this.amount = newAmount;
		}

	}

}
