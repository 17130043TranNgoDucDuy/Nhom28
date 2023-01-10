package edu.nlu.pharmacy_shop.dao;

import edu.nlu.pharmacy_shop.entity.*;
import edu.nlu.pharmacy_shop.util.DBUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ProductDAO implements Serializable {
	private static ProductDAO instance;

	private ProductDAO() {

	}

	public static ProductDAO getInstance() {
		if (instance == null)
			instance = new ProductDAO();

		return instance;
	}

	/**
	 * Returns a list of all categories except the parent category.
	 *
	 * @return List of category entities.
	 */
	public List<Category> findAllCategoryExceptParent() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT id, name FROM categories WHERE parent_id IS NOT NULL AND NOT enabled = 0";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String name = rs.getString(2);

					Category category = new Category();
					category.setId(id);
					category.setName(name);

					categories.add(category);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	/**
	 * Returns a list of all status.
	 *
	 * @return List of status entities.
	 */
	public List<Status> findAllStatus() {
		List<Status> statuses = new ArrayList<>();
		String sql = "SELECT id, name FROM status";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String name = rs.getString(2);

					Status status = new Status();
					status.setId(id);
					status.setName(name);

					statuses.add(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	/**
	 * Return list of statuses by product id.
	 *
	 * @param id The id of the product.
	 * @return List of Status entities.
	 */
	public List<Status> findStatusesByProductId(Integer id) {
		List<Status> statuses = new ArrayList<>();
		String sql = "SELECT s.* FROM product_status ur INNER JOIN status s ON ur.status_id = s.id "
				+ "WHERE ur.product_id = ?";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setInt(1, id);

			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					Integer statusId = rs.getInt(1);
					String name = rs.getString(2);

					Status status = new Status(statusId, name);

					statuses.add(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	/**
	 * Delete a user_role record from the database.
	 *
	 * @param productId The id of the product.
	 */
	public void deleteProductStatus(Integer productId) {
		String sql = "DELETE FROM product_status WHERE product_id = ?";
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setInt(1, productId);

			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Total number of products in the database.
	 *
	 * @return The number of product entities.
	 */

	public int count() {
		String sql = "SELECT COUNT(id) FROM products";
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
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
		String sql = "SELECT COUNT(id) FROM products WHERE name LIKE ?";
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
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
	 * Returns all instance of product with pagination and search.
	 *
	 * @param sortType  Specify the sort type, ASC or DESC.
	 * @param pageSize  Specify the number of records per page.
	 * @param sortField Specify the column name to sort.
	 * @param index     Specify the page index.
	 * @return List of product entities.
	 */
	public List<Product> findAll(String keyword, String sortField, String sortType, int pageSize, int index) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT id, main_image_path, name, price, percent_discount, quantity "
				+ "FROM products WHERE name LIKE ? " + "ORDER BY " + sortField + " " + sortType + " LIMIT ? OFFSET ?";

		// use try-with-resources Statement to auto close the connection.
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

			stm.setString(1, "%" + keyword + "%");
			stm.setInt(2, pageSize);
			stm.setInt(3, (index - 1) * pageSize);

			// use try-with-resources Statement to auto close the ResultSet.
			try (ResultSet rs = stm.executeQuery()) {
				// fetch data from result set
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String mainImagePath = rs.getString(2);
					String name = rs.getString(3);
					int price = rs.getInt(4);
					int percentDiscount = rs.getInt(5);
					int quantity = rs.getInt(6);

					Product product = new Product(id, mainImagePath, name, price, percentDiscount, quantity);
					findStatusesByProductId(id).forEach(product::addStatus);

					products.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Retrieves a product entity by its name.
	 *
	 * @param name A name specifying the name of the product.
	 * @return Product entity if the product entity with the given name exists, null
	 *         otherwise.
	 */
	public Product findByName(String name) {
		String sql = "SELECT id FROM products WHERE name LIKE ?";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setString(1, "%" + name + "%");

			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));

					return product;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Save a product to the database.
	 *
	 * @param product The product entity to be saved.
	 * @return True if the product is saved successfully, false otherwise.
	 * @throws SQLIntegrityConstraintViolationException If the name is already in
	 *                                                  use.
	 */
	public boolean save(Product product) {
		String sqlInsertProduct = "INSERT INTO products (main_image_path, name, alias, category_id, description, "
				+ "last_updated_time, price, percent_discount, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlInsertProductStatus = "INSERT INTO product_status (product_id, status_id) value(?, ?)";

		try (Connection conn = DBUtils.makeConnection();
				PreparedStatement productStm = conn.prepareStatement(sqlInsertProduct, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement productStatusStm = conn.prepareStatement(sqlInsertProductStatus)) {

			productStm.setString(1, product.getMainImagePath());
			productStm.setString(2, product.getName());
			productStm.setString(3, product.getAlias());
			productStm.setInt(4, product.getCategory().getId());
			productStm.setString(5, product.getDescription());
			productStm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			productStm.setInt(7, product.getPrice());
			productStm.setInt(8, product.getPercentDiscount());
			productStm.setInt(9, product.getQuantity());

			productStm.executeUpdate();

			// get the id of the inserted product
			ResultSet rs = productStm.getGeneratedKeys();
			int productKey;

			// If the result set is not empty, get the id
			if (rs.next()) {
				productKey = rs.getInt(1);

				List<Status> statuses = product.getStatuses();
				for (Status status : statuses) {
					productStatusStm.setInt(1, productKey);
					productStatusStm.setInt(2, status.getId());
					productStatusStm.addBatch();
				}

				// execute multiple commands at the same time
				if (productStatusStm.executeBatch().length > 0)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retrieves a product entity by its id.
	 *
	 * @param id An id specifying the id of the product.
	 * @return Product entity if the product entity with the given id exists, null
	 *         otherwise.
	 */
	public Product findById(Integer id) {
		String sql = "SELECT * FROM products WHERE id = ?";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setInt(1, id);

			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					String mainImagePath = rs.getString(2);
					String name = rs.getString(3);
					String alias = rs.getString(4);
					Integer categoryId = rs.getInt(5);
					String description = rs.getString(6);
					Date lastUpdatedTime = rs.getDate(7);
					int price = rs.getInt(8);
					int percentDiscount = rs.getInt(9);
					int quantity = rs.getInt(10);

					Category category = new Category();
					category.setId(categoryId);

					Product product = new Product(id, mainImagePath, name, alias, category, description,
							lastUpdatedTime, price, percentDiscount, quantity);
					findStatusesByProductId(id).forEach(product::addStatus);

					return product;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Update a product in the database.
	 *
	 * @param product The product entity to be updated.
	 * @return True if the product is updated successfully, false otherwise.
	 * @throws SQLIntegrityConstraintViolationException If the name is already in
	 *                                                  use.
	 */
	public boolean update(Product product) {
		String updateProductSql = "UPDATE products SET main_image_path = ?, name = ?, alias = ?, "
				+ "category_id = ?, description = ?, last_updated_time = ?, price = ?, percent_discount = ?, quantity = ?"
				+ " WHERE id = ?";
		String insertProductStatusSql = "INSERT INTO product_status (product_id, status_id) value(?, ?)";
		try (Connection conn = DBUtils.makeConnection();
				PreparedStatement productStm = conn.prepareStatement(updateProductSql);
				PreparedStatement productStatusStm = conn.prepareStatement(insertProductStatusSql)) {
			productStm.setString(1, product.getMainImagePath());
			productStm.setString(2, product.getName());
			productStm.setString(3, product.getAlias());
			productStm.setInt(4, product.getCategory().getId());
			productStm.setString(5, product.getDescription());
			productStm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			productStm.setInt(7, product.getPrice());
			productStm.setInt(8, product.getPercentDiscount());
			productStm.setInt(9, product.getQuantity());
			productStm.setInt(10, product.getId());

			productStm.executeUpdate();

			deleteProductStatus(product.getId());

			List<Status> statuses = product.getStatuses();
			for (Status status : statuses) {
				productStatusStm.setInt(1, product.getId());
				productStatusStm.setInt(2, status.getId());
				productStatusStm.addBatch();
			}

			// execute multiple commands at the same time
			if (productStatusStm.executeBatch().length > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete a book from the database.
	 *
	 * @param id The id of the user to be deleted.
	 */
	public void delete(Integer id) {
		String sql = "DELETE FROM products WHERE id = ?";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setInt(1, id);

			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns all instance of product with pagination and search.
	 *
	 * @param sortType  Specify the sort type, ASC or DESC.
	 * @param pageSize  Specify the number of records per page.
	 * @param sortField Specify the column name to sort.
	 * @param index     Specify the page index.
	 * @return List of product entities.
	 */
	public List<Product> findAllActive(String keyword, String sortField, String sortType, int pageSize, int index) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.id, p.main_image_path, p.name, p.price, p.percent_discount "
				+ "FROM products p LEFT JOIN product_status ps ON p.id = ps.product_id "
				+ "WHERE ps.status_id NOT IN (1, 4) AND name LIKE ? " + "ORDER BY " + sortField + " " + sortType
				+ " LIMIT ? OFFSET ?";

		// use try-with-resources Statement to auto close the connection.
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

			stm.setString(1, "%" + keyword + "%");
			stm.setInt(2, pageSize);
			stm.setInt(3, (index - 1) * pageSize);

			// use try-with-resources Statement to auto close the ResultSet.
			try (ResultSet rs = stm.executeQuery()) {
				// fetch data from result set
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String mainImagePath = rs.getString(2);
					String name = rs.getString(3);
					int price = rs.getInt(4);
					int percentDiscount = rs.getInt(5);

					Product product = new Product();
					product.setId(id);
					product.setMainImagePath(mainImagePath);
					product.setName(name);
					product.setPrice(price);
					product.setPercentDiscount(percentDiscount);

					products.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Total number of products in the database.
	 *
	 * @return The number of product entities.
	 */

	public int countActive() {
		String sql = "SELECT COUNT(id) FROM products LEFT JOIN product_status ps on products.id = ps.product_id "
				+ "WHERE ps.status_id NOT IN (1, 4)";
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
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
	public int countByKeywordActive(String keyword) {
		String sql = "SELECT COUNT(id) FROM products LEFT JOIN product_status ps on products.id = ps.product_id "
				+ "WHERE ps.status_id NOT IN (1, 4) AND name LIKE ?";
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
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

}
