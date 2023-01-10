package edu.nlu.pharmacy_shop.controller.backend.category;

import edu.nlu.pharmacy_shop.service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateCategoryServlet", value = "/backend/create_category")
public class CreateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryService(request, response);
        service.createCategory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryService(request, response);
        service.saveCategory();
    }
}
