package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.constant.Constants;
import edu.nlu.pharmacy_shop.dao.ProductDAO;
import edu.nlu.pharmacy_shop.dao.SettingDAO;
import edu.nlu.pharmacy_shop.entity.Product;
import edu.nlu.pharmacy_shop.entity.Setting;
import edu.nlu.pharmacy_shop.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShoppingCartService {
    private final ProductDAO productDAO = ProductDAO.getInstance();
    private final SettingDAO settingDAO = SettingDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ShoppingCartService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void showCart() throws ServletException, IOException {
        // setting
        List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

        for (Setting store : stores) {
            request.setAttribute(store.getKey(), store.getValue());
        }

        Object cart = request.getSession().getAttribute("cart");

        if (cart == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            request.getSession().setAttribute("cart", shoppingCart);
        }

        request.getRequestDispatcher("/frontend/cart.jsp").forward(request, response);
    }

    public void addToCart() throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("productId"));

        Object cart = request.getSession().getAttribute("cart");

        ShoppingCart shoppingCart;

        if (cart != null && cart instanceof ShoppingCart) {
            shoppingCart = (ShoppingCart) cart;
        } else {
            shoppingCart = new ShoppingCart();
            request.getSession().setAttribute("cart", shoppingCart);
        }

        Product product = productDAO.findById(productId);

        shoppingCart.addItem(product);

        String cartPage = request.getContextPath().concat("/cart");
        response.sendRedirect(cartPage);
    }
}
