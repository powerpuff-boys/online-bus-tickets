package com.fmi.edu.online.bus.tickets.processors;

public class PaymentDetails {
	private PaymentResult paymentResult;
	private String paymentMessage;

	public PaymentDetails(PaymentResult paymentResult, String paymentMessage) {
		this.paymentResult = paymentResult;
		this.paymentMessage = paymentMessage;
	}

	public PaymentResult getPaymentResult() {
		return paymentResult;
	}

	public String getPaymentMessage() {
		return paymentMessage;
	}

}
