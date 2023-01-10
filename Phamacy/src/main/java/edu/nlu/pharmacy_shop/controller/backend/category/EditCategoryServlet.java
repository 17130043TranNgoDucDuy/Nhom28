package edu.nlu.pharmacy_shop.controller.backend.category;

import edu.nlu.pharmacy_shop.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditCategoryServlet", value = "/backend/edit_category")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryService(request, response);
        service.editCategory();
    }
}
