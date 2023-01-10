package edu.nlu.pharmacy_shop.controller.frontend.cart;

import edu.nlu.pharmacy_shop.dao.ProductDAO;
import edu.nlu.pharmacy_shop.entity.Product;
import edu.nlu.pharmacy_shop.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveCartServlet", value = "/remove_from_cart")
public class RemoveCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));

        Object cart = request.getSession().getAttribute("cart");

        ShoppingCart shoppingCart = (ShoppingCart) cart;

        Product product = ProductDAO.getInstance().findById(productId);

        shoppingCart.removeItem(product);

        String cartPage = request.getContextPath().concat("/cart");
        response.sendRedirect(cartPage);
    }
}
