package edu.nlu.pharmacy_shop.controller.frontend.customer;

import edu.nlu.pharmacy_shop.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowCustomerRegisterServlet", value = "/register")
public class ShowCustomerRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService(request, response);
        customerService.showCustomerRegisterForm();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
