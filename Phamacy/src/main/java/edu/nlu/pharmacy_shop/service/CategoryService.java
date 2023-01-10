package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.nlu.pharmacy_shop.dao.CategoryDAO;
import edu.nlu.pharmacy_shop.util.FileUploadUtils;

import static edu.nlu.pharmacy_shop.constant.Constants.*;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.cleanDir;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.saveFile;

public class CategoryService {
    private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public CategoryService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Returns a list of categories that respond back to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listCategory() throws ServletException, IOException {
        listCategory(null);
    }

    /**
     * Returns a list of categories and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listCategory(String message) throws ServletException, IOException {
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

        int numberOfCategories = categoryDAO.count();
        int totalKeywordResults = categoryDAO.countByKeyword(keyword);

        List<Category> categories = categoryDAO.findAll(keyword, DEFAULT_SORT_FIELD, DEFAULT_SORT_TYPE, DEFAULT_PAGE_SIZE, pageNumber);

        long totalPages = totalKeywordResults / DEFAULT_PAGE_SIZE;
        if (numberOfCategories % DEFAULT_PAGE_SIZE != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfCategories", numberOfCategories);
        request.setAttribute("listCategories", categories);

        if (message != null)
            request.setAttribute("message", message);

        String listPage = "category.jsp";
        request.getRequestDispatcher(listPage).forward(request, response);
    }

    /**
     * Display category form to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void createCategory() throws ServletException, IOException {
        List<Category> allCategory = categoryDAO.findAllCategory();
        request.setAttribute("allCategory", allCategory);
        request.getRequestDispatcher("category-form.jsp").forward(request, response);
    }

    /**
     * Get category information from the form and save it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void saveCategory() throws ServletException, IOException {
        String name = request.getParameter("name");
        String alias = request.getParameter("alias");
        Integer parentId = Integer.valueOf(request.getParameter("parent"));
        boolean enabled = "on".equals(request.getParameter("enabled"));

        if (alias.isEmpty()) {
            alias = name.replaceAll(" ", "-");
        }

        Category category = new Category(name, alias, parentId, enabled);
        boolean nameExists = categoryDAO.checkNameExists(name);
        String message;

        if (nameExists) {
            message = "Thể loại " + name + " đã tồn tại";
            request.setAttribute("message", message);
            request.setAttribute("category", category);
            createCategory();
        } else {
            categoryDAO.save(category);
            message = "Thể loại " + name + " đã được thêm thành công";
            listCategory(message);
        }
    }

    /**
     * Get the categories infornamtions by id from request and display it to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void editCategory() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Category categoryById = categoryDAO.findById(id);

        String message;

        if (categoryById == null) {
            message = "Không tìm thấy thể loại";
            listCategory(message);
        } else {
            request.setAttribute("category", categoryById);
            request.setAttribute("title", "Chỉnh sửa thể loại");
            createCategory();
        }
    }

    /**
     * Get the category's information from the request and update it into the database.
     *
     * @throws ServletException If the request for the POST could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request
     */
    public void updateCategory() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String alias = request.getParameter("alias");
        Integer parentId = Integer.valueOf(request.getParameter("parent"));
        boolean enabled = "on".equals(request.getParameter("enabled"));

        Category category = new Category(id, name, alias, parentId, enabled);

        Category categoryById = categoryDAO.findById(id);
        Category categoryByName = categoryDAO.findByName(name);

        String message;

        if (categoryById == null) {
            message = "Không tìm thấy thể loại";
            listCategory(message);
        } else {
            if (categoryByName != null && id != categoryByName.getId()) {
                message = "Thể loại " + name + " đã tồn tại!!!";
                request.setAttribute("message", message);

                request.setAttribute("category", category);

                request.setAttribute("title", "Chỉnh sửa thể loại");
                createCategory();
            } else {
                categoryDAO.update(category);

                message = "Thể loại " + name + " đã được cập nhật thành công !";
                listCategory(message);
            }
        }
    }

    /**
     * Get the category id and enable status from the request and update it to the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void enabledCategory() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

        Category category = categoryDAO.findById(id);

        String message;

        if (category == null) {
            message = "Không tìm thấy thể loại hoặc thể loại đã bị xóa";
        } else {
            categoryDAO.updateEnabledStatus(id, enabled);
            message = "Thể loại " + category.getName() + " đã được " + (enabled ? "kích hoạt" : "vô hiệu hóa") + " thành công !";
        }
        listCategory(message);
    }
}
