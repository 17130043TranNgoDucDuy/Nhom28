package edu.nlu.pharmacy_shop.entity;



import java.util.Objects;


public class Role {
    private Integer id;
    private String name;
    private String description;
    public Role(Integer id) {
        this.id = id;
    }
    
    public Role(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id) && name.equals(role.name) && description.equals(role.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}