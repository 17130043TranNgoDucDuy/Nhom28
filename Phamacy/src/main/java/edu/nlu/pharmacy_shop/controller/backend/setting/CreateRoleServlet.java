package edu.nlu.pharmacy_shop.controller.backend.setting;

import edu.nlu.pharmacy_shop.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateRoleServlet", urlPatterns = "/backend/create_role")
public class CreateRoleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleService service = new RoleService(request, response);

        service.save();
    }
}
