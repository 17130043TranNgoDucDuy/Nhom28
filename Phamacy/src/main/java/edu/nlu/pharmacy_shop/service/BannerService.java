package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.dao.BannerDAO;
import edu.nlu.pharmacy_shop.entity.Banner;
import edu.nlu.pharmacy_shop.entity.Category;
import edu.nlu.pharmacy_shop.entity.Employee;
import edu.nlu.pharmacy_shop.util.FileUploadUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static edu.nlu.pharmacy_shop.constant.Constants.*;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.*;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.removeDir;

public class BannerService {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final BannerDAO bannerDAO = BannerDAO.getInstance();

    public BannerService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Returns a list of banners that respond back to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listBanner() throws ServletException, IOException {
        listBanner(null);
    }

    /**
     * Returns a list of banners and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listBanner(String message) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword == null)
            keyword = "";
        String pageNumberString = request.getParameter("pageNumber");
        int pageNumber;

        if (pageNumberString == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        int numberOfBanners = bannerDAO.count();
        int totalKeywordResults = bannerDAO.countByKeyword(keyword);

        List<Banner> banners = bannerDAO.findAll(keyword, "created_time", "DESC", DEFAULT_PAGE_SIZE, pageNumber);

        long totalPages = totalKeywordResults / DEFAULT_PAGE_SIZE;
        if (numberOfBanners % DEFAULT_PAGE_SIZE != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfBanners", numberOfBanners);
        request.setAttribute("listBanners", banners);

        if (message != null)
            request.setAttribute("message", message);

        String listPage = "banner.jsp";
        request.getRequestDispatcher(listPage).forward(request, response);
    }

    /**
     * Display banner form to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void createBanner() throws ServletException, IOException {
        request.getRequestDispatcher("banner-form.jsp").forward(request, response);
    }

    /**
     * Read banner information submitted from the form.
     *
     * @return A brand object with information read from form.
     */
    public Banner readAllBannerFields(Banner banner) {
        String name = request.getParameter("name");
        boolean enabled = "on".equals(request.getParameter("enabled"));

        banner.setName(name);
        banner.setEnabled(enabled);

        return banner;
    }

    /**
     * Get banner information from the form and save it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void saveBanner() throws ServletException, IOException {
        Banner banner = new Banner();
        banner = readAllBannerFields(banner);

        Part part = request.getPart("image");
        String fileName = part.getSubmittedFileName();

        boolean existImagePath = bannerDAO.checkImagePathExists(fileName);

        if (existImagePath) {
            String message = "???nh qu???ng c??o " + fileName + " ???? t???n t???i!!!";
            banner.setImagePath(null);
            request.setAttribute("message", message);
            request.setAttribute("banner", banner);

            createBanner();
        } else {
            banner.setImagePath(fileName);
            bannerDAO.save(banner);

            Integer id = bannerDAO.findByImagePath(fileName).getId();
            String serverPath = request.getServletContext().getRealPath("");
            String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY + File.separator;
            String nameDirectoryServer = "banner" + File.separator + id;

            directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;
            saveFile(directoryServerPath, fileName, part);
            String fileServerPath = directoryServerPath + File.separator + fileName;
            copyFile(fileServerPath, nameDirectoryServer);

            String message = banner.getName() + " ???? ???????c th??m th??nh c??ng !";
            listBanner(message);
        }
    }

    /**
     * Get banner information corresponding to the given ID from the database and display it in the form.
     *
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request.
     * @throws ServletException If the request for the GET could not be handled.
     */
    public void editBanner() throws IOException, ServletException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Banner banner = bannerDAO.findById(id);

        if (banner == null) {
            String message = "Kh??ng t??m th???y qu???ng c??o n??y";
            listBanner(message);
        } else {
            request.setAttribute("banner", banner);
            request.setAttribute("title", "Ch???nh s???a qu???ng c??o");

            createBanner();
        }
    }

    /**
     * Get the banner's information from the request and update it into the database.
     *
     * @throws ServletException If the request for the POST could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request
     */
    public void updateBanner() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Banner banner = new Banner();
        banner = readAllBannerFields(banner);
        banner.setId(id);

        Part part = request.getPart("image");
        String fileName = part.getSubmittedFileName();

        Banner bannerById = bannerDAO.findById(id);
        Banner bannerByImagePath = bannerDAO.findByImagePath(fileName);

        String message;

        if (bannerById == null) {
            message = "Kh??ng t??m th???y qu???ng c??o n??y";
            listBanner(message);
        } else {
            if (bannerByImagePath != null && id != bannerByImagePath.getId()) {
                message = "???nh qu???ng c??o " + bannerByImagePath.getImagePath() + " ???? t???n t???i!!!";
                banner.setImagePath(bannerById.getImagePath());
                request.setAttribute("message", message);
                request.setAttribute("banner", banner);
                request.setAttribute("title", "Ch???nh s???a qu???ng c??o");

                createBanner();
            } else {
                if (fileName.isEmpty()) {
                    banner.setImagePath(bannerById.getImagePath());
                } else {
                    banner.setImagePath(fileName);
                    String serverPath = request.getServletContext().getRealPath("");
                    String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;

                    String nameDirectoryServer = "banner" + File.separator + id;
                    directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

                    cleanDir(directoryServerPath);
                    cleanDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);

                    saveFile(directoryServerPath, fileName, part);
                    String fileServerPath = directoryServerPath + File.separator + fileName;
                    copyFile(fileServerPath, nameDirectoryServer);
                }

                bannerDAO.update(banner);

                message = "Qu???ng c??o ???? ???????c c???p nh???t th??nh c??ng !";
                listBanner(message);
            }
        }
    }

    /**
     * Get the banner's id and enable status from the request and update it to the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void enabledBanner() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

        Banner banner = bannerDAO.findById(id);

        String message;

        if (banner == null) {
            message = "Kh??ng t??m th???y qu???ng c??o ho???c qu???ng c??o n??y ???? b??? x??a";
        } else {
            bannerDAO.updateEnabledStatus(id, enabled);
            message = "Qu???ng c??o n??y ???? ???????c " + (enabled ? "k??ch ho???t" : "v?? hi???u h??a") + " th??nh c??ng !";
        }

        listBanner(message);
    }

    /**
     * Get the banner's id from the request and delete it from the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void deleteBanner() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Banner banner = bannerDAO.findById(id);

        String message;

        if (banner == null) {
            message = "Kh??ng t??m th???y qu???ng c??o ho???c qu???ng c??o ???? b??? x??a";
        } else {
            bannerDAO.delete(id);
            String serverPath = request.getServletContext().getRealPath("");
            String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;
            String nameDirectoryServer = "banner" + File.separator + id;
            directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

            removeDir(directoryServerPath);
            removeDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);

            message = "Qu???ng c??o ???? ???????c x??a th??nh c??ng !";
        }
        listBanner(message);
    }
}
