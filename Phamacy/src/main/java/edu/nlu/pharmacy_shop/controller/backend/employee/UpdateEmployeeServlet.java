package edu.nlu.pharmacy_shop.controller.backend.employee;

import edu.nlu.pharmacy_shop.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateEmployeeServlet", value = "/backend/update_employee")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 1024, // 1 MB
        maxRequestSize = 1024 * 1024 * 2 // 2 MB
)
public class UpdateEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService(request, response);
        service.updateEmployee();
    }
}
