package edu.nlu.pharmacy_shop.dao;

import edu.nlu.pharmacy_shop.entity.Banner;
import edu.nlu.pharmacy_shop.entity.Setting;
import edu.nlu.pharmacy_shop.util.DBUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDAO implements Serializable {
	private static SettingDAO instance;

	private SettingDAO() {

	}

	public static SettingDAO getInstance() {
		if (instance == null)
			instance = new SettingDAO();

		return instance;
	}

	/**
	 * Find all setting by category.
	 *
	 * @param category Category of setting.
	 * @return List of setting entities.
	 */
	public List<Setting> findAllByCategory(String category) {
		List<Setting> settings = new ArrayList<>();
		String sql = "SELECT `key`, value FROM settings WHERE category = ?";

		// use try-with-resources Statement to auto close the connection.
		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

			stm.setString(1, category);

			// use try-with-resources Statement to auto close the ResultSet.
			try (ResultSet rs = stm.executeQuery()) {
				// fetch data from result set
				while (rs.next()) {
					String key = rs.getString(1);
					String value = rs.getString(2);

					settings.add(new Setting(key, value));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return settings;
	}

	/**
	 * Update a setting in the database.
	 *
	 * @param setting The setting entity to be updated.
	 * @return True if the setting is updated successfully, false otherwise.
	 */
	public boolean updateSettingStore(Setting setting) {
		String sql = "UPDATE settings SET value = ? WHERE `key` = ?";

		try (Connection conn = DBUtils.makeConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setString(1, setting.getValue());
			stm.setString(2, setting.getKey());

			return stm.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
