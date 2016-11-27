package storefront;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import dbConnection.DBConnection;
import dbConnection.DBConnection.DBName;
import eCommerce.InventoryModel;

public class InventoryDAO {

	public List<InventoryModel> getAvailableInventoryList() throws SQLException, ServletException {
		Connection c = null;
		try {
			c = DBConnection.getConnection(DBName.MANI);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from inventory where available_quantity > 0");
			List<InventoryModel> inventoryList = new ArrayList<>();

			while (rs.next()) {
				InventoryModel inventory = new InventoryModel();
				inventory.setId(rs.getString("INVENTORY_PGUID"));
				inventory.setName(rs.getString("INVENTORY_NAME"));
				inventory.setDescription(rs.getString("INVENTORY_DESC"));
				inventory.setPrice(rs.getFloat("INVENTORY_PRICE_PER_UNIT"));
				inventory.setQuantity(rs.getInt("AVAILABLE_QUANTITY"));
				inventory.setAddedDate(rs.getDate("CREATED_DATETIME"));
				inventoryList.add(inventory);
			}

			return inventoryList;
		} finally {
			if (c != null) {
				c.close();
			}
		}
	}

	public InventoryModel getInventoryItem(String id) throws SQLException, ServletException {
		if (id != null && !id.isEmpty()) {
			Connection c = null;
			try {
				c = DBConnection.getConnection(DBName.MANI);

				PreparedStatement stmt = c.prepareStatement("select * from inventory where inventory_pguid = ?");
				stmt.setInt(1, Integer.valueOf(id));
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					InventoryModel inventory = new InventoryModel();
					inventory.setId(rs.getString("INVENTORY_PGUID"));
					inventory.setName(rs.getString("INVENTORY_NAME"));
					inventory.setDescription(rs.getString("INVENTORY_DESC"));
					inventory.setPrice(rs.getFloat("INVENTORY_PRICE_PER_UNIT"));
					inventory.setQuantity(rs.getInt("AVAILABLE_QUANTITY"));
					inventory.setAddedDate(rs.getDate("CREATED_DATETIME"));
					return inventory;
				}

			} finally {
				if (c != null) {
					c.close();
				}
			}
		}
		return null;
	}
}
