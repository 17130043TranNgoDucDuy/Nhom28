package edu.nlu.pharmacy_shop.controller.backend.banner;

import edu.nlu.pharmacy_shop.service.BannerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet", value = "/backend/delete_banner")
public class DeleteBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerService service = new BannerService(request, response);
        service.deleteBanner();
    }
}
