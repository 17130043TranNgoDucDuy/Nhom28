package edu.nlu.pharmacy_shop.controller.backend.customer;

import edu.nlu.pharmacy_shop.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListCustomerServlet", urlPatterns = "/backend/list_customers")
public class ListCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService service = new CustomerService(request, response);

        service.listCustomer();
    }
}
