package edu.nlu.pharmacy_shop.controller.frontend.customer;

import edu.nlu.pharmacy_shop.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterCustomerServlet", value = "/register_customer")
public class RegisterCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService(request, response);
        customerService.saveCustomer();
    }
}
