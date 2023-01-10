package edu.nlu.pharmacy_shop.entity;


public class OrderDetail {
	private Integer id;
	private Order orderID;
	private Product productID;
	private Integer product_cost;
	private float total;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Order getOrderID() {
		return orderID;
	}
	public void setOrderID(Order orderID) {
		this.orderID = orderID;
	}
	public Product getProductID() {
		return productID;
	}
	public void setProductID(Product productID) {
		this.productID = productID;
	}
	public Integer getProduct_cost() {
		return product_cost;
	}
	public void setProduct_cost(Integer product_cost) {
		this.product_cost = product_cost;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	

}
