package edu.nlu.pharmacy_shop.controller.backend.product;

import edu.nlu.pharmacy_shop.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", value = "/backend/delete_product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService service = new ProductService(request, response);
        service.deleteProduct();
    }
}
