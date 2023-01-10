package edu.nlu.pharmacy_shop.controller.backend.product;

import edu.nlu.pharmacy_shop.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateProductServlet", value = "/backend/update_product")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 1024, // 1 MB
        maxRequestSize = 1024 * 1024 * 2 // 2 MB
)
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService service = new ProductService(request, response);
        service.updateProduct();
    }
}
