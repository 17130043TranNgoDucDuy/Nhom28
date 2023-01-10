package edu.nlu.pharmacy_shop.controller.backend.setting.store;

import edu.nlu.pharmacy_shop.service.SettingStoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowSettingStoreServlet", value = "/backend/setting_stores")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 1024, // 1 MB
        maxRequestSize = 1024 * 1024 * 2 // 2 MB
)
public class ShowSettingStoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingStoreService service = new SettingStoreService(request, response);
        service.showSettingStore();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingStoreService service = new SettingStoreService(request, response);
        service.updateSettingStore();
    }
}
