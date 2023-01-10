package edu.nlu.pharmacy_shop.entity;


public class Category {
	private Integer id;
	private String name;
	private String alias;
	private Integer parentId;
	private boolean enabled;

	public Category(String name, String alias, Integer parentId, boolean enabled) {
		this.name = name;
		this.alias = alias;
		this.parentId = parentId;
		this.enabled = enabled;
	}
	

	public Category() {
		super();
	}


	public Category(Integer id, String name, String alias, Integer parentId, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.parentId = parentId;
		this.enabled = enabled;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}