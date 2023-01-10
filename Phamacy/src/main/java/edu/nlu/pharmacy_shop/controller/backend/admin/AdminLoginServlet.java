package edu.nlu.pharmacy_shop.controller.backend.admin;

import edu.nlu.pharmacy_shop.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet", value = "/backend/login")
public class AdminLoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2340170755747529556L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService(request, response);
        service.checkLogin();
    }
}
