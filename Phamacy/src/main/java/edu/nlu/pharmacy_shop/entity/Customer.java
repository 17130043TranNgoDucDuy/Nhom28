package edu.nlu.pharmacy_shop.entity;

import java.util.Date;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String email;
    private String password;
    private Date createdTime;
    private String authenticationType;
    private String verificationCode;
    private boolean enabled;

    public Customer(String firstName, String lastName, String phoneNumber, Address address, String email, String password, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Customer(Integer id, String firstName, String lastName, String phoneNumber, String email, Address address, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.enabled = enabled;
    }

    public Customer(Integer id, String firstName, String lastName, String phoneNumber, Address address, String email, Date createdTime, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.createdTime = createdTime;
        this.enabled = enabled;
    }

    public Customer(Integer id, String firstName, String lastName, String phoneNumber, Address address, String email, String password, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }
    

	public Customer(Integer id, String firstName, String lastName, String phoneNumber, Address address, String email,
			String password, Date createdTime, String authenticationType, String verificationCode, boolean enabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.password = password;
		this.createdTime = createdTime;
		this.authenticationType = authenticationType;
		this.verificationCode = verificationCode;
		this.enabled = enabled;
	}

	public Customer() {
		super();
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
    
}