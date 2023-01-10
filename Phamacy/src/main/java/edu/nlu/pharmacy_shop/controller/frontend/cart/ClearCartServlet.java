package edu.nlu.pharmacy_shop.controller.frontend.cart;

import edu.nlu.pharmacy_shop.entity.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ClearCartServlet", value = "/clear_cart")
public class ClearCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
        shoppingCart.clear();
        String cartPage = request.getContextPath().concat("/cart");
        response.sendRedirect(cartPage);
    }
}
