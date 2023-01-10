package edu.nlu.pharmacy_shop.entity;

import java.sql.Date;


public class Order {
	private int id;
	private Customer customerID;
	private Date order_time;
	private boolean payment_method;
	private float price;
	private double discount;
	private float tax;
	private float total;
	private boolean delivery_form;
	private short status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public boolean isPayment_method() {
		return payment_method;
	}
	public void setPayment_method(boolean payment_method) {
		this.payment_method = payment_method;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public boolean isDelivery_form() {
		return delivery_form;
	}
	public void setDelivery_form(boolean delivery_form) {
		this.delivery_form = delivery_form;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	
	

	

}
