package edu.nlu.pharmacy_shop.controller.frontend.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutCustomerServlet", value = "/logout")
public class LogoutCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loggedCustomer");
        response.sendRedirect(request.getContextPath());
    }
}
