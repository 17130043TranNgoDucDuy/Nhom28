package edu.nlu.pharmacy_shop.controller.backend.banner;

import edu.nlu.pharmacy_shop.service.BannerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ListBannerServlet", value = "/backend/list_banners")
public class ListBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerService service = new BannerService(request, response);
        service.listBanner();
    }
}
