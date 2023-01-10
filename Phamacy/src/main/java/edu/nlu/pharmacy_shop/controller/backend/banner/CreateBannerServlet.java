package edu.nlu.pharmacy_shop.controller.backend.banner;

import edu.nlu.pharmacy_shop.service.BannerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateBannerServlet", value = "/backend/create_banner")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 1024, // 1 MB
        maxRequestSize = 1024 * 1024 * 2 // 2 MB
)
public class CreateBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerService service = new BannerService(request, response);
        service.createBanner();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerService service = new BannerService(request, response);
        service.saveBanner();
    }
}
