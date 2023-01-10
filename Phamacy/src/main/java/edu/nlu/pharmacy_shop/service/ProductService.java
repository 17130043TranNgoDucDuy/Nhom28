package edu.nlu.pharmacy_shop.service;

import edu.nlu.pharmacy_shop.constant.Constants;
import edu.nlu.pharmacy_shop.dao.ProductDAO;
import edu.nlu.pharmacy_shop.dao.SettingDAO;
import edu.nlu.pharmacy_shop.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.nlu.pharmacy_shop.constant.Constants.*;
import static edu.nlu.pharmacy_shop.util.FileUploadUtils.*;

public class ProductService {
    private final ProductDAO productDAO = ProductDAO.getInstance();
    private final SettingDAO settingDAO = SettingDAO.getInstance();
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ProductService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Returns a list of products that respond back to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listProduct() throws ServletException, IOException {
        listProduct(null);
    }

    /**
     * Returns a list of products and a response message to the user.
     *
     * @param message A message specified to display to the user
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void listProduct(String message) throws ServletException, IOException {
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

        int numberOfProducts = productDAO.count();
        int totalKeywordResults = productDAO.countByKeyword(keyword);

        List<Product> products = productDAO.findAll(keyword, DEFAULT_SORT_FIELD, DEFAULT_SORT_TYPE, DEFAULT_PAGE_SIZE, pageNumber);

        long totalPages = totalKeywordResults / DEFAULT_PAGE_SIZE;
        if (numberOfProducts % DEFAULT_PAGE_SIZE != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfProducts", numberOfProducts);
        request.setAttribute("listproducts", products);

        if (message != null)
            request.setAttribute("message", message);

        String listPage = "product.jsp";
        request.getRequestDispatcher(listPage).forward(request, response);
    }

    /**
     * Displays all categories and brands from database to product form.
     */
    public void getCategoriesAndBrandsDisplayedAdminInterface() {
        List<Category> categories = productDAO.findAllCategoryExceptParent();
        List<Status> statuses = productDAO.findAllStatus();
        request.setAttribute("categories", categories);
        request.setAttribute("statuses", statuses);
    }

    /**
     * Display product form to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void createProduct() throws ServletException, IOException {
        getCategoriesAndBrandsDisplayedAdminInterface();
        request.getRequestDispatcher("product-form.jsp").forward(request, response);
    }

    /**
     * Read product information submitted from the form.
     *
     * @return A product object with information read from form.
     */
    public Product readAllEmployeeFieldsExceptRole(Product product) {
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Integer brandId = Integer.parseInt(request.getParameter("brand"));
        String name = request.getParameter("name");
        String alias = request.getParameter("alias");
        if (alias.isEmpty()) {
            alias = name.replaceAll(" ", "-");
        }
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int percentDiscount = Integer.parseInt(request.getParameter("percentDiscount"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String[] statuses = request.getParameterValues("statuses");

        for (String statusId : statuses) {
            product.addStatus(new Status(Integer.parseInt(statusId)));
        }

        Category category = new Category();
        category.setId(categoryId);

        product.setCategory(category);
        product.setName(name);
        product.setAlias(alias);
        product.setDescription(description);
        product.setPrice(price);
        product.setPercentDiscount(percentDiscount);
        product.setQuantity(quantity);

        return product;
    }

    /**
     * Get employee information from the form and save it in the database.
     *
     * @throws ServletException If the request for the POST could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request.
     */
    public void saveProduct() throws ServletException, IOException {
        Product product = new Product();
        product = readAllEmployeeFieldsExceptRole(product);

        Product existProduct = productDAO.findByName(product.getName());
        String message;
        if (existProduct != null) {
            message = "Sản phẩm " + product.getName() + " đã tồn tại!!!";
            product.setMainImagePath(null);

            request.setAttribute("message", message);
            request.setAttribute("product", product);

            createProduct();
        } else {
            Part part = request.getPart("image");
            String fileName = part.getSubmittedFileName();

            product.setMainImagePath(fileName);
            productDAO.save(product);

            Integer id = productDAO.findByName(product.getName()).getId();
            String serverPath = request.getServletContext().getRealPath("");
            String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;
            String nameDirectoryServer = "product" + File.separator + id;

            directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;
            saveFile(directoryServerPath, fileName, part);
            String fileServerPath = directoryServerPath + File.separator + fileName;
            copyFile(fileServerPath, nameDirectoryServer);

            message = "Sản phẩm " + product.getName() + " đã được thêm thành công !";
            listProduct(message);
        }
    }

    /**
     * Get status of product corresponding from database and display it to user.
     *
     * @param product The product to get status.
     * @return A map of status and checked status.
     */
    public Map<Status, String> getSelectedStatuses(Product product) {
        Map<Status, String> roleMap = new HashMap<>();

        productDAO.findAllStatus().forEach(status -> roleMap.put(status, ""));
        product.getStatuses().forEach(status -> roleMap.put(status, "checked"));
        return roleMap;
    }

    /**
     * Get the product's infornamtions by id from request and display it to user.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void editProduct() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Product product = productDAO.findById(id);

        Map<Status, String> statusMap = getSelectedStatuses(product);

        request.setAttribute("statusMap", statusMap);

        String message;

        if (product == null) {
            message = "Không tìm thấy sản phẩm";
            listProduct(message);
        } else {
            request.setAttribute("product", product);
            request.setAttribute("title", "Chỉnh sửa sản phẩm");
            createProduct();
        }
    }

    /**
     * Get the product's information from the request and update it into the database.
     *
     * @throws ServletException If the request for the POST could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the POST request
     */
    public void updateProduct() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Product product = new Product();
        product = readAllEmployeeFieldsExceptRole(product);
        product.setId(id);

        Product productById = productDAO.findById(id);
        Product productByName = productDAO.findByName(product.getName());

        String message;

        if (productById == null) {
            message = "Không tìm thấy sản phẩm";
            listProduct(message);
        } else {
            if (productByName != null && id != productByName.getId()) {
                message = "Sản phẩm " + product.getName() + " đã tồn tại";

                Map<Status, String> statusMap = getSelectedStatuses(productById);

                request.setAttribute("message", message);
                request.setAttribute("statusMap", statusMap);
                request.setAttribute("product", product);
                request.setAttribute("title", "Chỉnh sửa sản phẩm");

                createProduct();
            } else {
                Part part = request.getPart("image");
                String fileName = part.getSubmittedFileName();

                String serverPath = request.getServletContext().getRealPath("");
                String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;

                if (!fileName.isEmpty()) {
                    product.setMainImagePath(fileName);
                    String nameDirectoryServer = "product" + File.separator + id;
                    directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

                    cleanDir(directoryServerPath);
                    cleanDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);


                    saveFile(directoryServerPath, fileName, part);
                    String fileServerPath = directoryServerPath + File.separator + fileName;
                    copyFile(fileServerPath, nameDirectoryServer);

                    productDAO.update(product);
                } else {
                    product.setMainImagePath(productById.getMainImagePath());
                    productDAO.update(product);
                }

                message = "Sản phẩm " + product.getName() + " đã được cập nhật thành công !";
                listProduct(message);
            }
        }
    }

    /**
     * Get the product's id from the request and delete it from the database.
     *
     * @throws ServletException If the request for the GET could not be handled
     * @throws IOException      If an input or output error is detected when the servlet handles the GET request
     */
    public void deleteProduct() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        Product product = productDAO.findById(id);

        String message;

        if (product == null) {
            message = "Không tìm thấy sản phẩm hoặc nhân viên đã bị xóa";
        } else {
            productDAO.delete(id);
            String serverPath = request.getServletContext().getRealPath("");
            String directoryServerPath = serverPath + File.separator + DEFAULT_IMAGE_DIRECTORY;
            String nameDirectoryServer = "product" + File.separator + id;
            directoryServerPath = directoryServerPath + File.separator + nameDirectoryServer;

            removeDir(directoryServerPath);
            removeDir(DEFAULT_APP_IMAGE_DIRECTORY + nameDirectoryServer);

            message = "Sản phẩm " + product.getName() + " đã được xóa thành công !";
        }
        listProduct(message);
    }

    /*--------------------Frontend--------------------*/

    /**
     * Show the product page to user.
     */
    public void showProduct() throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        request.setAttribute("title", keyword);

        // setting
        List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

        for (Setting store : stores) {
            request.setAttribute(store.getKey(), store.getValue());
        }



        if (keyword == null || keyword.equals("xe máy"))
            keyword = "";
        String pageNumberString = request.getParameter("pageNumber");
        int pageNumber;

        if (pageNumberString == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        int numberOfProducts = productDAO.countActive();
        int totalKeywordResults = productDAO.countByKeywordActive(keyword);

        List<Product> products = productDAO.findAllActive(keyword, "last_updated_time", "DESC", 9, pageNumber);

        int totalPages = totalKeywordResults / 9;
        if (numberOfProducts % 9 != 0) totalPages++;

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("numberOfProducts", numberOfProducts);
        request.setAttribute("listProducts", products);

        request.getRequestDispatcher("/frontend/shop.jsp").forward(request, response);
    }

    public void showProductDetail() throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Product product = productDAO.findById(id);
        request.setAttribute("product", product);

        // setting
        List<Setting> stores = settingDAO.findAllByCategory(Constants.GENERAL_SETTING_CATEGORY);

        for (Setting store : stores) {
            request.setAttribute(store.getKey(), store.getValue());
        }

        request.getRequestDispatcher("/frontend/product-details.jsp").forward(request, response);
    }
}
