package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.constant.Constants;
import edu.nlu.pharmacy_shop.dao.CategoryDAO;
import edu.nlu.pharmacy_shop.dao.CustomerDAO;
import edu.nlu.pharmacy_shop.dao.SettingDAO;
import edu.nlu.pharmacy_shop.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static edu.nlu.pharmacy_shop.constant.Constants.DEFAULT_PAGE_SIZE;

public class CustomerService {
    private final CustomerDAO customerDAO = CustomerDAO.getInstance();
    private final SettingDAO settingDAO = SettingDAO.getInstance();
    private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public CustomerService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Returns a list of customers that respond back to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listCustomer() throws ServletException, IOException {
        listCustomer(null);
    }

    /**
     * Returns a list of customers and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listCustomer(String message) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword == null)
            keyword = "";
        String pageNumberString = request.getParameter("pageNumber");
        int pageNumber;

        if (pageNumberString == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        long numberOfCustomers = customerDAO.count();
        int totalKeywordResults = customerDAO.countByKeyword(keyword);

        List<Customer> customers = customerDAO.findAll(keyword, "created_time", "DESC", DEFAULT_PAGE_SIZE, pageNumber);

        long totalPages = totalKeywordResults / DEFAULT_PAGE_SIZE;
        if (numberOfCustomers % DEFAULT_PAGE_SIZE != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfCustomers", numberOfCustomers);
        request.setAttribute("listCustomers", customers);

        if (message != null)
            request.setAttribute("message", message);

        String listPage = "customer.jsp";
        request.getRequestDispatcher(listPage).forward(request, response);
    }

    /**
     * Get the customer's id and enable status from the request and update it to the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void enabledCustomer() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

        Customer customer = customerDAO.findById(id);

        String message;

        if (customer == null) {
            message = "Không tìm thấy khách hàng hoặc khách hàng đã bị xóa";
        } else {
            customerDAO.updateEnabledStatus(id, enabled);
            message = "Khách hàng " + customer.getLastName() + " " + customer.getFirstName() + " đã được "
                    + (enabled ? "kích hoạt" : "vô hiệu hóa") + " thành công !";
        }
        listCustomer(message);
    }

    /**
     * Get the customer's id from the request and delete it from the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void deleteCustomer() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Customer customer = customerDAO.findById(id);

        String message;

        if (customer == null) {
            message = "Không tìm thấy khách hàng hoặc khách hàng đã bị xóa";
        } else {
            customerDAO.delete(id);
            message = "Khách hàng " + customer.getLastName() + " " + customer.getFirstName() + " đã được xóa thành công !";
        }
        listCustomer(message);
    }

    /*-----------login client----------*/

    /**
     * Display the register page to the user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void showCustomerRegisterForm() throws ServletException, IOException {
        showCustomerRegisterForm(null);
    }

    /**
     * Display the register page and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void showCustomerRegisterForm(String message) throws ServletException, IOException {
        if (message != null) request.setAttribute("message", message);

        String registerPage = "frontend/register.jsp";
        request.getRequestDispatcher(registerPage).forward(request, response);
    }

    /**
     * Get the customer's information from the request.
     *
     * @return A customer object
     */
    private void readAllcustomerFields(Customer customer) {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String ward = request.getParameter("ward");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        boolean enabled = true;

        Address address = new Address(street, ward, district, city);

        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhoneNumber(phone);
        customer.setAddress(address);
        customer.setEnabled(enabled);
    }

    /**
     * Get customer information from the form and save it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void saveCustomer() throws ServletException, IOException {
        Customer customer = new Customer();
        readAllcustomerFields(customer);
        boolean existcustomer = customerDAO.checkEmailExists(customer.getEmail());

        if (existcustomer) {
            String message = "Email " + customer.getEmail() + " đã tồn tại!!!";

            request.setAttribute("customer", customer);
            request.setAttribute("message", message);

            showCustomerRegisterForm(message);
        } else {
            customerDAO.save(customer);

            String homePage = "frontend/index.jsp";
            request.getRequestDispatcher(homePage).forward(request, response);
        }
    }

    /**
     * Display the login page to the user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void showLogin() throws ServletException, IOException {
        showLogin(null);
    }

    /**
     * Display the login page and a response message if information is incorrect.
     *
     * @param message A message to notify if the information is incorrect
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void showLogin(String message) throws ServletException, IOException {
        if (message != null) request.setAttribute("message", message);
        String loginPage = "frontend/login.jsp";
        request.getRequestDispatcher(loginPage).forward(request, response);
    }

    /**
     * Get email and password from the request and check if account exists will redirect to the home page.
     * If the account does not exist, it will redirect to the login page and message will be displayed.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void doLogin() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = HashGenerator.generateMD5(request.getParameter("password"));

        Customer customer = customerDAO.login(email, password);
        boolean existCustomer = customerDAO.checkEmailExists(email);

        if (!existCustomer) {
            String message = "Email không tồn tại";
            request.setAttribute("message", message);
            showLogin(message);
        } else {
            if (customer == null) {
                String message = "Email hoặc mật khẩu không đúng";
                request.setAttribute("message", message);
                showLogin(message);
            } else if (!customer.isEnabled() && customer != null) {
                String message = "Tài khoản của bạn đã bị khóa";
                request.setAttribute("message", message);
                showLogin(message);
            } else {
                request.getSession().setAttribute("loggedCustomer", customer);
                List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

                for (Setting store : stores) {
                    request.setAttribute(store.getKey(), store.getValue());
                }

                // category
                List<Category> parents = categoryDAO.findAllParentCategory();
                List<Category> childs = categoryDAO.findAllChildCategory();
                request.setAttribute("parents", parents);
                request.setAttribute("childs", childs);

                String homePage = "frontend/index.jsp";
                request.getRequestDispatcher(homePage).forward(request, response);
            }
        }
    }

    /**
     * Show the customer's profile to the user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void showAccountInfo() throws ServletException, IOException {
        String accountInfoPage = "frontend/account.jsp";
        List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

        for (Setting store : stores) {
            request.setAttribute(store.getKey(), store.getValue());
        }
        request.getRequestDispatcher(accountInfoPage).forward(request, response);
    }

    /**
     * Get customer information from the form and update it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void updateAccountInfo() throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
        String password = request.getParameter("password");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer addressId = Integer.parseInt(request.getParameter("addressId"));
        readAllcustomerFields(customer);
        customer.setId(id);
        customer.setPassword(password);
        customer.getAddress().setId(addressId);

        customerDAO.update(customer);

        showAccountInfo();
    }
}
