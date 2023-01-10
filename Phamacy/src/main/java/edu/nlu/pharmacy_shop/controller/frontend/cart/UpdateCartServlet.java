package edu.nlu.pharmacy_shop.controller.frontend.cart;

import edu.nlu.pharmacy_shop.entity.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "UpdateCartServlet", value = "/update_cart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] arrProductIds = request.getParameterValues("productId");
        String[] arrQuantities = new String[arrProductIds.length];

        for (int i = 1; i <= arrQuantities.length; i++) {
            String quantity = request.getParameter("quantity" + i);
            arrQuantities[i - 1] = quantity;
        }

        int[] productIds = Arrays.stream(arrProductIds).mapToInt(Integer::parseInt).toArray();
        int[] quantities = Arrays.stream(arrQuantities).mapToInt(Integer::parseInt).toArray();

        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
        shoppingCart.updateCart(productIds, quantities);

        String cartPage = request.getContextPath().concat("/cart");
        response.sendRedirect(cartPage);
    }
}
