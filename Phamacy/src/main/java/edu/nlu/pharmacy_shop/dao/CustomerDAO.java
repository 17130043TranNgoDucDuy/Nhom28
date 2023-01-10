package edu.nlu.pharmacy_shop.dao;

import edu.nlu.pharmacy_shop.entity.Address;
import edu.nlu.pharmacy_shop.entity.Customer;
import edu.nlu.pharmacy_shop.entity.HashGenerator;
import edu.nlu.pharmacy_shop.util.DBUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomerDAO implements Serializable {
    private static CustomerDAO instance;

    private CustomerDAO() {

    }

    public static CustomerDAO getInstance() {
        if (instance == null)
            instance = new CustomerDAO();

        return instance;
    }

    /**
     * Get address by customer id.
     *
     * @param customerId The id of the user.
     * @return The address entity.
     */
    public Address findAddressByCustomerId(Integer customerId) {
        String sql = "SELECT street, ward, district, city FROM addresses WHERE customer_id = ?";
        Address address = null;

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, customerId);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String street = rs.getString(1);
                    String ward = rs.getString(2);
                    String district = rs.getString(3);
                    String city = rs.getString(4);

                    address = new Address(street, ward, district, city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * Insert a new address into the database
     *
     * @param address    The address to be inserted.
     * @param customerId The id of the customer who owns the address.
     */
    public void insertAddress(Address address, int customerId) {
        String sql = "INSERT INTO addresses (street, ward, district, city, customer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, address.getStreet());
            stm.setString(2, address.getWard());
            stm.setString(3, address.getDistrict());
            stm.setString(4, address.getCity());
            stm.setInt(5, customerId);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an address record in the database.
     *
     * @param address    The address to be updated.
     * @param customerId The id of the customer who owns the address.
     */
    public void updateAddress(Address address, int customerId) {
        String sql = "UPDATE addresses SET street = ?, ward = ?, district = ?, city = ? WHERE customer_id = ? AND id = ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, address.getStreet());
            stm.setString(2, address.getWard());
            stm.setString(3, address.getDistrict());
            stm.setString(4, address.getCity());
            stm.setInt(5, customerId);
            stm.setInt(6, address.getId());

            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all instance of customer with pagination
     *
     * @param keyword   The keyword to search
     * @param sortType  Specify the sort type, ASC or DESC.
     * @param pageSize  Specify the number of records per page.
     * @param sortField Specify the column name to sort.
     * @param index     Specify the page index.
     * @return List of Customer entities.
     */
    public List<Customer> findAll(String keyword, String sortField, String sortType, int pageSize, int index) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, email, phone_number, created_time, enabled " +
                "FROM customers WHERE CONCAT(email, ' ', first_name, ' ', last_name) LIKE ? " +
                "ORDER BY " + sortField + " " + sortType + " LIMIT ? OFFSET ?";

        // use try-with-resources Statement to auto close the connection.
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, "%" + keyword + "%");
            stm.setInt(2, pageSize);
            stm.setInt(3, (index - 1) * pageSize);

            // use try-with-resources Statement to auto close the ResultSet.
            try (ResultSet rs = stm.executeQuery()) {
                // fetch data from result set
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String email = rs.getString(4);
                    String phoneNumber = rs.getString(5);
                    Date date = rs.getTimestamp(6);
                    boolean enabled = rs.getBoolean(7);

                    Address address = findAddressByCustomerId(id);

                    Customer customer = new Customer(id, firstName, lastName, phoneNumber, address, email, date, enabled);
                    customers.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Check if the email is already in use.
     *
     * @param email The email to be checked.
     * @return True if the email is already in use, false otherwise.
     */
    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(email) FROM customers WHERE email = ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Save a customer to the database.
     *
     * @param customer The customer entity to be saved.
     * @return True if the customer is saved successfully, false otherwise.
     * @throws SQLIntegrityConstraintViolationException If the email is already in use.
     */
    public boolean save(Customer customer) {
        String sql = "INSERT INTO customers (first_name, last_name, phone_number, email, password, " +
                "created_time, enabled) value(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, customer.getFirstName());
            stm.setString(2, customer.getLastName());
            stm.setString(3, customer.getPhoneNumber());
            stm.setString(4, customer.getEmail());
            stm.setString(5, HashGenerator.generateMD5(customer.getPassword()));
            stm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stm.setBoolean(7, customer.isEnabled());

            stm.executeUpdate();

            // get the id of the inserted user
            ResultSet rs = stm.getGeneratedKeys();

            // If the result set is not empty, get the id
            if (rs.next()) {
                int customerId = rs.getInt(1);

                insertAddress(customer.getAddress(), customerId);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a Customer entity by its id.
     *
     * @param id An id specifying the id of the Customer.
     * @return Customer entity if the Customer entity with the given id exists, null otherwise.
     */
    public Customer findById(Integer id) {
        String sql = "SELECT c.*, a.id, a.street, a.ward, a.district, a.city " +
                "FROM customers c  LEFT JOIN addresses a ON c.id = a.customer_id WHERE c.id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String phoneNumber = rs.getString(4);
                    String email = rs.getString(5);
                    String password = rs.getString(6);
                    Date createdTime = rs.getDate(7);
                    String authenticationType = rs.getString(8);
                    String verificationCode = rs.getString(9);
                    boolean enabled = rs.getBoolean(10);
                    Integer addressId = rs.getInt(11);
                    String street = rs.getString(12);
                    String ward = rs.getString(13);
                    String district = rs.getString(14);
                    String city = rs.getString(15);

                    Address address = new Address(addressId, street, ward, district, city);

                    return new Customer(id, firstName, lastName, phoneNumber, address, email, password, createdTime, authenticationType, verificationCode, enabled);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update a customer in the database.
     *
     * @param customer The employee entity to be updated.
     * @return True if the employee is updated successfully, false otherwise.
     * @throws SQLIntegrityConstraintViolationException If the email is already in use.
     */

    public boolean update(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, phone_number = ?, " +
                "password = ?, enabled = ? WHERE id = ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, customer.getFirstName());
            stm.setString(2, customer.getLastName());
            stm.setString(3, customer.getPhoneNumber());
            stm.setString(4, customer.getPassword());
            stm.setBoolean(5, customer.isEnabled());
            stm.setInt(6, customer.getId());

            stm.executeUpdate();

            updateAddress(customer.getAddress(), customer.getId());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a customer from the database.
     *
     * @param id The id of the customer to be deleted.
     */

    public void delete(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, id);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update enabled status of a customer.
     *
     * @param id      The id of the customer to be updated.
     * @param enabled The updated enabled status.
     */
    public void updateEnabledStatus(Integer id, boolean enabled) {
        String sql = "UPDATE customers SET enabled = ? WHERE id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setBoolean(1, enabled);
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Total number of users in the database.
     *
     * @return The number of Customer entities.
     */
    public long count() {
        String sql = "SELECT COUNT(id) FROM customers";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next())
                    return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Total results keyword search.
     *
     * @return The number of results. 0 if no results.
     */
    public int countByKeyword(String keyword) {
        String sql = "SELECT COUNT(id) FROM customers WHERE CONCAT(email, ' ', first_name, ' ', last_name) LIKE ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next())
                    return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Get the customer information corresponding to the given email and password.
     *
     * @param email    The email of the customer.
     * @param password The password of the customer.
     * @return The customer corresponding to the given email and password. Null if no such customer exists.
     */
    public Customer login(String email, String password) {
        String sql = "SELECT c.*, a.id, a.street, a.ward, a.district, a.city " +
                "FROM customers c LEFT JOIN addresses a ON c.id = a.customer_id WHERE email = ? AND password = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String phoneNumber = rs.getString(4);
                    boolean enabled = rs.getBoolean(10);
                    Integer addressId = rs.getInt(11);
                    String street = rs.getString(12);
                    String ward = rs.getString(13);
                    String district = rs.getString(14);
                    String city = rs.getString(15);

                    Address address = new Address(addressId, street, ward, district, city);

                    return new Customer(id, firstName, lastName, phoneNumber, address, email, password, enabled);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
