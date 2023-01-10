package edu.nlu.pharmacy_shop.controller.backend.employee;

import edu.nlu.pharmacy_shop.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditEmployeeServlet", value = "/backend/edit_employee")
public class EditEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService(request, response);
        service.editEmployee();
    }
}
