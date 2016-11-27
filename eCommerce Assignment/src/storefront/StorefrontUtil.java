package storefront;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import eCommerce.InventoryModel;

public class StorefrontUtil {
	static InventoryModel getMatchingItem(List<InventoryModel> inventoryList, String id)
			throws SQLException, ServletException {
		for (InventoryModel inventoryModel : inventoryList) {
			if (inventoryModel.getId().equals(id)) {
				return inventoryModel;
			}
		}
		return null;
	}

	static List<InventoryModel> getDisplayableInventoryList(List<InventoryModel> dbInventoryList,
			ShoppingCart shoppingCart) throws SQLException, ServletException {
		if (shoppingCart != null && !shoppingCart.isEmpty()) {
			List<InventoryModel> displayableInventoryList = new ArrayList<>();
			for (InventoryModel dbInventoryModel : dbInventoryList) {
				ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItem(dbInventoryModel.getId());
				if (shoppingCartItem == null
						|| dbInventoryModel.getQuantity() > shoppingCartItem.getQuantityOrdered()) {
					displayableInventoryList.add(dbInventoryModel);
				}
			}
			return displayableInventoryList;
		}
		return dbInventoryList;
	}
}
