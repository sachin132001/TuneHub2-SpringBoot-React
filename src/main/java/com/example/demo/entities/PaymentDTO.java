package com.example.demo.entities;

public class PaymentDTO {
	
	String orderId;
	String paymentId;
    String signature;
	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentDTO(String orderId, String paymentId, String signature) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.signature = signature;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "PaymentDTO [orderId=" + orderId + ", paymentId=" + paymentId + ", signature=" + signature + "]";
	}
    

}

