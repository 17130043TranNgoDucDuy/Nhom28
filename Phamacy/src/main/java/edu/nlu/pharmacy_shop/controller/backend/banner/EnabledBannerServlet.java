package edu.nlu.pharmacy_shop.controller.backend.banner;

import edu.nlu.pharmacy_shop.service.BannerService;
import edu.nlu.pharmacy_shop.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EnabledBannerServlet", value = "/backend/enable_banner")
public class EnabledBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerService service = new BannerService(request, response);
        service.enabledBanner();
    }
}
