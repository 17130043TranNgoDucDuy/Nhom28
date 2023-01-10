package edu.nlu.pharmacy_shop.controller.backend.category;

import edu.nlu.pharmacy_shop.service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ListCategoryServlet", value = "/backend/list_categories")
public class ListCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryService(request, response);
        service.listCategory();
    }
}
