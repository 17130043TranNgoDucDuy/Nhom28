package edu.nlu.pharmacy_shop.controller.backend.employee;

import edu.nlu.pharmacy_shop.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateEmployeeServlet", value = "/backend/create_employee")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 1024, // 1 MB
        maxRequestSize = 1024 * 1024 * 2 // 2 MB
)
public class CreateEmployeeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService(request, response);

        service.createEmployee();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService(request, response);

        service.saveEmployee();
    }
}
