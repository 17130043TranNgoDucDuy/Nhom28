package edu.nlu.pharmacy_shop.entity;

import java.util.Date;

public class Banner {
	private Integer id;
	private String imagePath;
	private String name;
	private Date createdTime;
	private boolean enabled;
	
	

	public Banner() {
		super();
	}
	

	public Banner(Integer id, String imagePath, String name, Date createdTime, boolean enabled) {
		super();
		this.id = id;
		this.imagePath = imagePath;
		this.name = name;
		this.createdTime = createdTime;
		this.enabled = enabled;
	}


	public Banner(String imagePath, String name, boolean enabled) {
		this.imagePath = imagePath;
		this.name = name;
		this.enabled = enabled;
	}

	public Banner(String imagePath, String name, Date createdTime, boolean enabled) {
		this.imagePath = imagePath;
		this.name = name;
		this.createdTime = createdTime;
		this.enabled = enabled;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}