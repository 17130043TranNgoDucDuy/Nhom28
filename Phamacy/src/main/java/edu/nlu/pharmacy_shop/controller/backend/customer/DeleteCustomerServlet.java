package edu.nlu.pharmacy_shop.controller.backend.customer;

import edu.nlu.pharmacy_shop.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCustomerServlet", value = "/backend/delete_customer")
public class DeleteCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService service = new CustomerService(request, response);
        service.deleteCustomer();
    }
}
