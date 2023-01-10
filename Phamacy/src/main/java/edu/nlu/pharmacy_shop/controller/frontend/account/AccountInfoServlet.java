package edu.nlu.pharmacy_shop.controller.frontend.account;

import edu.nlu.pharmacy_shop.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountInfoServlet", value = "/account-info")
public class AccountInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService(request, response);
        customerService.showAccountInfo();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService(request, response);
        customerService.updateAccountInfo();
    }
}
