package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.constant.Constants;
import edu.nlu.pharmacy_shop.dao.BannerDAO;
import edu.nlu.pharmacy_shop.dao.CategoryDAO;
import edu.nlu.pharmacy_shop.dao.SettingDAO;
import edu.nlu.pharmacy_shop.entity.Banner;
import edu.nlu.pharmacy_shop.entity.Category;
import edu.nlu.pharmacy_shop.entity.Setting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeService {
    private final SettingDAO settingDAO = SettingDAO.getInstance();
    private final BannerDAO bannerDAO = BannerDAO.getInstance();
    private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public HomeService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void showHome() throws IOException, ServletException {
        String homepage = "frontend/index.jsp";

        // banner
        List<Banner> banners = bannerDAO.findAllEnabled("created_time", "DESC");
        int totalBanner = banners.size();
        request.setAttribute("banners", banners);
        request.setAttribute("totalBanner", totalBanner);

        // setting
        List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

        for (Setting store : stores) {
            request.setAttribute(store.getKey(), store.getValue());
        }

        // category
        List<Category> parents = categoryDAO.findAllParentCategory();
        List<Category> childs = categoryDAO.findAllChildCategory();
        request.setAttribute("parents", parents);
        request.setAttribute("childs", childs);

        request.getRequestDispatcher(homepage).forward(request, response);
    }
}
