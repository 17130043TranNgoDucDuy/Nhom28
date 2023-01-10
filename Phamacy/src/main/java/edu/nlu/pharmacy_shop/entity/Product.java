package edu.nlu.pharmacy_shop.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private Integer id;
	private String mainImagePath;
	private String name;
	private String alias;
	private Category category;
	private String description;
	private Date updatedTime;
	private int price;
	private int percentDiscount;
	private int quantity;
	private List<Status> statuses = new ArrayList<>();
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Product(String mainImagePath, String name, String alias, Category category, String description,
			int price, int percentDiscount, int quantity) {
		this.mainImagePath = mainImagePath;
		this.name = name;
		this.alias = alias;
		this.category = category;
		this.description = description;
		this.price = price;
		this.percentDiscount = percentDiscount;
		this.quantity = quantity;
	}
	

	public Product() {
		super();
	}


	public Product(Integer id, String mainImagePath, String name, String alias, Category category,
			String description, Date updatedTime, int price, int percentDiscount, int quantity) {
		this.id = id;
		this.mainImagePath = mainImagePath;
		this.name = name;
		this.alias = alias;
		this.category = category;
		this.description = description;
		this.updatedTime = updatedTime;
		this.price = price;
		this.percentDiscount = percentDiscount;
		this.quantity = quantity;
	}

	public Product(Integer id, String mainImagePath, String name, int price, int percentDiscount,
			int quantity) {
		this.id = id;
		this.mainImagePath = mainImagePath;
		this.name = name;
		this.price = price;
		this.percentDiscount = percentDiscount;
		this.quantity = quantity;
	}

	public void addStatus(Status status) {
		this.statuses.add(status);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", mainImagePath='" + mainImagePath + '\'' + ", name='" + name + '\''
				+ ", alias='" + alias + '\'' + ", category=" + category + ", description='"
				+ description + '\'' + ", updatedTime=" + updatedTime + ", price=" + price + ", percentDiscount="
				+ percentDiscount + ", quantity=" + quantity + ", statuses=" + statuses + '}';
	}
}