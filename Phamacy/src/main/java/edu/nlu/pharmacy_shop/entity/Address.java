package edu.nlu.pharmacy_shop.entity;


public class Address {
    private Integer id;
    private String street;
    private String ward;
    private String district;
    private String city;
    private Employee employee;
    private Customer customer;
//    private Order order;

    public Address(String street, String ward, String district, String city) {
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address(Integer id, String street, String ward, String district, String city) {
        this.id = id;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }
    
    public static void main(String[] args) {
		Address d ;
		
	}
}