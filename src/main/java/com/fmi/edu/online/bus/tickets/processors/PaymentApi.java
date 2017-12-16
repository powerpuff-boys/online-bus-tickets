package com.fmi.edu.online.bus.tickets.processors;

public class PaymentApi {
	
	private PaymentExecutor executor;
	
	public PaymentApi(PaymentExecutor executor){
		this.executor = executor;
	}
	
	public PaymentDetails createPayment(UserPaymentDetails paymentDetails){
		try{
			AuthorizationCheck authorized = executor.authorizeUser(paymentDetails.getUserName(), paymentDetails.getPassword());
			if (authorized == AuthorizationCheck.BAD_CREDENTIALS) {
				return new PaymentDetails(PaymentResult.FAILED, "Username or password is incorrect");
			}
			
			boolean isPaymentEligible = executor.ensurePayment(paymentDetails.getUserName(), paymentDetails.getPassword());
			if (!isPaymentEligible) {
				return new PaymentDetails(PaymentResult.FAILED, "Not enough cash amount");
			}
			
			boolean isPaymentSuccessful = executor.executePayment(paymentDetails.getUserName(), paymentDetails.getPassword());
			if (!isPaymentSuccessful) {
				return new PaymentDetails(PaymentResult.FAILED, "Error occured during payment execution");
			}
			
			return new PaymentDetails(PaymentResult.DONE, "Payment done successfully");
		}catch(Exception e){
			throw e;
		}
	}
}
