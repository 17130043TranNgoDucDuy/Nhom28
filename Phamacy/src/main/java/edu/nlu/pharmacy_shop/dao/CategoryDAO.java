package edu.nlu.pharmacy_shop.dao;

import edu.nlu.pharmacy_shop.entity.Category;
import edu.nlu.pharmacy_shop.util.DBUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO implements Serializable {
    private static CategoryDAO instance;

    private CategoryDAO() {

    }

    public static CategoryDAO getInstance() {
        if (instance == null)
            instance = new CategoryDAO();

        return instance;
    }

    /**
     * Returns all instance of Category with pagination
     *
     * @param sortType  Specify the sort type, ASC or DESC.
     * @param pageSize  Specify the number of records per page.
     * @param sortField Specify the column name to sort.
     * @param index     Specify the page index.
     * @return List of Category entities.
     */
    public List<Category> findAll(String keyword, String sortField, String sortType, int pageSize, int index) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE name LIKE ? " +
                "ORDER BY " + sortField + " " + sortType + " LIMIT ? OFFSET ?";

        // use try-with-resources Statement to auto close the connection.
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, "%" + keyword + "%");
            stm.setInt(2, pageSize);
            stm.setInt(3, (index - 1) * pageSize);

            // use try-with-resources Statement to auto close the ResultSet.
            try (ResultSet rs = stm.executeQuery()) {
                // fetch data from result set
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String name = rs.getString(2);
                    String alias = rs.getString(3);
                    int parentId = rs.getInt(4);
                    boolean enabled = rs.getBoolean(5);

                    categories.add(new Category(id, name, alias, parentId, enabled));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * Save a Category to the database.
     *
     * @param category The Category entity to be saved.
     * @return True if the Category is saved successfully, false otherwise.
     */
    public boolean save(Category category) {
        String sql = "INSERT INTO categories (name, alias, parent_id, enabled) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, category.getName());
            stm.setString(2, category.getAlias());

            if (category.getParentId() == 0)
                stm.setNull(3, Types.INTEGER);
            else
                stm.setInt(3, category.getParentId());

            stm.setBoolean(4, category.isEnabled());

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check if the name is already used.
     *
     * @param name The name to be checked.
     * @return True if the name is already in use, false otherwise.
     */
    public boolean checkNameExists(String name) {
        String sql = "SELECT COUNT(name) FROM categories WHERE name = ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves a Category entity by its id.
     *
     * @param id An id specifying the id of the Category.
     * @return Category entity if the Category entity with the given id exists, null otherwise.
     */
    public Category findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString(2);
                    String alias = rs.getString(3);
                    Integer parentId = rs.getInt(4);
                    boolean enabled = rs.getBoolean(5);

                    return new Category(id, name, alias, parentId, enabled);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update a Category in the database.
     *
     * @param category The Category entity to be updated.
     * @return True if the Category is updated successfully, false otherwise.
     * @throws SQLIntegrityConstraintViolationException If the name is already in use.
     */
    public boolean update(Category category) {
        String sql = "UPDATE categories SET name = ?, alias = ?, parent_id = ?, enabled = ? WHERE id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, category.getName());
            stm.setString(2, category.getAlias());

            if (category.getParentId() == 0)
                stm.setNull(3, Types.INTEGER);
            else
                stm.setInt(3, category.getParentId());

            stm.setBoolean(4, category.isEnabled());
            stm.setInt(5, category.getId());

            return stm.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Retrieves a category entity by its name.
     *
     * @param name A name specifying the name of the category.
     * @return Category entity if the Category entity with the given name exists, null otherwise.
     */
    public Category findByName(String name) {
        String sql = "SELECT id, name FROM categories WHERE name = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);

                    return category;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Total number of categories in the database.
     *
     * @return The number of category entities.
     */
    public int count() {
        String sql = "SELECT COUNT(id) FROM categories";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next())
                    return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Total results keyword search.
     *
     * @return The number of results. 0 if no results.
     */
    public int countByKeyword(String keyword) {
        String sql = "SELECT COUNT(id) FROM categories WHERE name LIKE ?";
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next())
                    return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Update enabled status of a Category.
     *
     * @param id      The id of the Category to be updated.
     * @param enabled The updated enabled status.
     */
    public void updateEnabledStatus(Integer id, boolean enabled) {
        String sql = "UPDATE categories SET enabled = ? WHERE id = ?";

        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setBoolean(1, enabled);
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all categories.
     *
     * @return List of Category entities.
     */
    public List<Category> findAllCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name, parent_id FROM categories";

        // use try-with-resources Statement to auto close the connection.
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            // use try-with-resources Statement to auto close the ResultSet.
            try (ResultSet rs = stm.executeQuery()) {
                // fetch data from result set
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String name = rs.getString(2);
                    Integer parentId = rs.getInt(3);

                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);
                    category.setParentId(parentId);

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * Returns all parent categories.
     *
     * @return List of category entities.
     */
    public List<Category> findAllParentCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT name, alias FROM categories WHERE parent_id IS NULL AND NOT enabled = 0";

        // use try-with-resources Statement to auto close the connection.
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            // use try-with-resources Statement to auto close the ResultSet.
            try (ResultSet rs = stm.executeQuery()) {
                // fetch data from result set
                while (rs.next()) {
                    String name = rs.getString(1);
                    String alias = rs.getString(2);

                    Category category = new Category();
                    category.setName(name);
                    category.setAlias(alias);

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * Returns all child categories.
     *
     * @return List of category entities.
     */
    public List<Category> findAllChildCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name, alias FROM categories WHERE parent_id IS NOT NULL AND NOT enabled = 0";

        // use try-with-resources Statement to auto close the connection.
        try (Connection conn = DBUtils.makeConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            // use try-with-resources Statement to auto close the ResultSet.
            try (ResultSet rs = stm.executeQuery()) {
                // fetch data from result set
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String name = rs.getString(2);
                    String alias = rs.getString(3);

                    Category category = new Category();
                    category.setId(id);
                    category.setName(name);
                    category.setAlias(alias);

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
}
