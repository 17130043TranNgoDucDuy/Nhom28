package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.dao.EmployeeDAO;
import edu.nlu.pharmacy_shop.dao.RoleDAO;
import edu.nlu.pharmacy_shop.entity.Address;
import edu.nlu.pharmacy_shop.entity.Employee;
import edu.nlu.pharmacy_shop.entity.HashGenerator;
import edu.nlu.pharmacy_shop.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.nlu.pharmacy_shop.constant.Constants.*;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.*;

public class EmployeeService {
    private final EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    private final RoleDAO roleDAO = RoleDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public EmployeeService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Returns a list of employees that respond back to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listEmployee() throws ServletException, IOException {
        listEmployee(null);
    }

    /**
     * Returns a list of employees and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listEmployee(String message) throws ServletException, IOException {
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

        long numberOfEmployees = employeeDAO.count();
        int totalKeywordResults = employeeDAO.countByKeyword(keyword);

        List<Employee> employees = employeeDAO.findAll(keyword, DEFAULT_SORT_FIELD, DEFAULT_SORT_TYPE, DEFAULT_PAGE_SIZE, pageNumber);

        long totalPages = totalKeywordResults / DEFAULT_PAGE_SIZE;
        if (numberOfEmployees % DEFAULT_PAGE_SIZE != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfEmployees", numberOfEmployees);
        request.setAttribute("listEmployees", employees);

        if (message != null)
            request.setAttribute("message", message);

        String listPage = "employee.jsp";
        request.getRequestDispatcher(listPage).forward(request, response);
    }

    /**
     * Display employee form to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void createEmployee() throws ServletException, IOException {
        List<Role> roles = roleDAO.findAllRolesExceptAdmin();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("employee-form.jsp").forward(request, response);
    }

    /**
     * Read employee information submitted from the form.
     *
     * @return An employee object with information read from form.
     */
    public Employee readAllEmployeeFieldsExceptRole(Employee employee) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String street = request.getParameter("street");
        String ward = request.getParameter("ward");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        boolean enabled = "on".equals(request.getParameter("enabled"));
        String[] roles = request.getParameterValues("roles");

        for (String roleId : roles) {
            employee.addRole(new Role(Integer.parseInt(roleId)));
        }

        Address address = new Address(street, ward, district, city);

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setAddress(address);
        employee.setPhoneNumber(phone);
        employee.setEnabled(enabled);

        return employee;
    }

    /**
     * Get employee information from the form and save it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void saveEmployee() throws ServletException, IOException {
        Employee employee = new Employee();
        employee = readAllEmployeeFieldsExceptRole(employee);

        boolean existEmployee = employeeDAO.checkEmailExists(employee.getEmail());

        if (existEmployee) {
            String message = "Email " + employee.getEmail() + " đã tồn tại!!!";
            request.setAttribute("message", message);
            request.setAttribute("employee", employee);

            createEmployee();
        } else {
            Part part = request.getPart("image");
            String fileName = part.getSubmittedFileName();

            if (!fileName.isEmpty()) {
                employee.setImagePath(fileName);
                employeeDAO.save(employee);

                Integer id = employeeDAO.findByEmail(employee.getEmail()).getId();
                String serverPath = request.getServletContext().getRealPath("");
                String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;
                String nameDirectoryServer = "employee" + File.separator + id;

                directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;
                saveFile(directoryServerPath, fileName, part);
                String fileServerPath = directoryServerPath + File.separator + fileName;
                copyFile(fileServerPath, nameDirectoryServer);
            } else
                employeeDAO.save(employee);

            String message = "Nhân viên " + employee.getLastName() + " " + employee.getFirstName() + " đã được thêm thành công !";
            listEmployee(message);
        }
    }

    /**
     * Get role of employee corresponding from database and display it to user.
     *
     * @param employee The employee to get role.
     * @return A map of role and checked status.
     */
    public Map<Role, String> getSelectedRoles(Employee employee) {
        Map<Role, String> roleMap = new HashMap<>();

        RoleDAO.getInstance().findAllRolesExceptAdmin().forEach(role -> roleMap.put(role, ""));
        employee.getRoles().forEach(role -> roleMap.put(role, "checked"));
        return roleMap;
    }

    /**
     * Get the employee's infornamtions by id from request and display it to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void editEmployee() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Employee employee = employeeDAO.findById(id);

        Map<Role, String> roleMap = getSelectedRoles(employee);

        request.setAttribute("roleMap", roleMap);

        String message;

        if (employee == null) {
            message = "Không tìm thấy nhân viên";
            listEmployee(message);
        } else {
            request.setAttribute("employee", employee);
            request.setAttribute("title", "Chỉnh sửa nhân viên");
            createEmployee();
        }
    }

    /**
     * Get the employee's information from the request and update it into the database.
     *
     * @throws ServletException If the request for the POST could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request
     */
    public void updateEmployee() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer addressId = Integer.valueOf(request.getParameter("addressId"));

        Employee employee = new Employee();
        employee = readAllEmployeeFieldsExceptRole(employee);
        employee.setId(id);
        employee.getAddress().setId(addressId);

        Employee employeeById = employeeDAO.findById(id);
        Employee employeeByEmail = employeeDAO.findByEmail(employee.getEmail());

        String message;

        if (employeeById == null) {
            message = "Không tìm thấy nhân viên";
            listEmployee(message);
        } else {
            if (employeeByEmail != null && id != employeeByEmail.getId()) {
                message = "Email " + employee.getEmail() + " đã tồn tại!!!";
                request.setAttribute("message", message);

                Map<Role, String> roleMap = getSelectedRoles(employeeById);
                employee.setImagePath(employeeById.getImagePath());

                request.setAttribute("roleMap", roleMap);
                request.setAttribute("employee", employee);

                request.setAttribute("title", "Chỉnh sửa nhân viên");

                createEmployee();
            } else {

                if (!employee.getPassword().isEmpty())
                    employee.setPassword(HashGenerator.generateMD5(employee.getPassword()));
                else
                    employee.setPassword(employeeById.getPassword());

                Part part = request.getPart("image");
                String fileName = part.getSubmittedFileName();

                if (!fileName.isEmpty()) {
                    employee.setImagePath(fileName);
                    String serverPath = request.getServletContext().getRealPath("");
                    String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;

                    String nameDirectoryServer = "employee" + File.separator + id;
                    directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

                    cleanDir(directoryServerPath);
                    cleanDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);


                    saveFile(directoryServerPath, fileName, part);
                    String fileServerPath = directoryServerPath + File.separator + fileName;
                    copyFile(fileServerPath, nameDirectoryServer);
                } else {
                    employee.setImagePath(employeeById.getImagePath());
                }

                request.getSession().setAttribute("email", employee.getEmail());
                request.getSession().setAttribute("firstName", employee.getFirstName());
                request.getSession().setAttribute("lastName", employee.getLastName());
                request.getSession().setAttribute("imagePath", employee.getImagePath());
                request.getSession().setAttribute("id", employee.getId());

                employeeDAO.update(employee);
                message = "Nhân viên " + employee.getLastName() + " " + employee.getFirstName() + " đã được cập nhật thành công !";
                listEmployee(message);
            }
        }
    }

    /**
     * Get the employee's id from the request and delete it from the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void deleteEmployee() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Employee employee = employeeDAO.findById(id);

        String message;

        if (employee == null) {
            message = "Không tìm thấy nhân viên hoặc nhân viên đã bị xóa";
        } else {
            employeeDAO.delete(id);
            String serverPath = request.getServletContext().getRealPath("");
            String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;
            String nameDirectoryServer = "employee" + File.separator + id;
            directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

            removeDir(directoryServerPath);
            removeDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);

            message = "Nhân viên " + employee.getLastName() + " " + employee.getFirstName() + " đã được xóa thành công !";
        }
        listEmployee(message);
    }

    /**
     * Get the employee's id and enable status from the request and update it to the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void enabledEmployee() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

        Employee employee = employeeDAO.findById(id);

        String message;

        if (employee == null) {
            message = "Không tìm thấy nhân viên hoặc nhân viên đã bị xóa";
        } else {
            employeeDAO.updateEnabledStatus(id, enabled);
            message = "Nhân viên " + employee.getLastName() + " " + employee.getFirstName() + " đã được "
                    + (enabled ? "kích hoạt" : "vô hiệu hóa") + " thành công !";
        }
        listEmployee(message);
    }

    /**
     * Check login information and redirect to the appropriate page.
     *
     * @throws ServletException If the request for the POST could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request
     */
    public void checkLogin() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = HashGenerator.generateMD5(request.getParameter("password"));

        Employee employee = employeeDAO.login(email, password);

        if (employee != null) {
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("firstName", employee.getFirstName());
            request.getSession().setAttribute("lastName", employee.getLastName());
            request.getSession().setAttribute("imagePath", employee.getImagePath());
            request.getSession().setAttribute("id", employee.getId());

            request.getRequestDispatcher("/backend/").forward(request, response);
        } else {
            String message = "Email hoặc mật khẩu không đúng";
            request.setAttribute("message", message);

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
