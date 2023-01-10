package edu.nlu.pharmacy_shop.entity;

import java.util.HashSet;
import java.util.Set;

public class Employee {
	private Integer id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String imagePath;
	private String email;
	private String password;
	private boolean enabled;
	private Set<Role> roles = new HashSet<>();
	private Address address;

	public Employee(Integer id, String firstName, String lastName, String phoneNumber, Address address,
			String imagePath, String email, boolean enabled) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.imagePath = imagePath;
		this.email = email;
		this.enabled = enabled;
	}

	public Employee(String firstName, String lastName, String phoneNumber, Address address, String imagePath,
			String email, String password, boolean enabled) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.imagePath = imagePath;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public Employee(Integer id, String firstName, String lastName, String phoneNumber, Address address,
			String imagePath, String email, String password, boolean enabled) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.imagePath = imagePath;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public Employee(Integer id, String firstName, String lastName, String email, String imagePath) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.imagePath = imagePath;
	}

	public Employee(Integer id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Employee(String firstName, String lastName, String phoneNumber, Address address, String email,
			String password, boolean enabled) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}
	

	public Employee() {
		super();
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", password='" + password + '\''
				+ ", enabled=" + enabled + ", roles=" + roles + ", address=" + address + '}';
	}
}