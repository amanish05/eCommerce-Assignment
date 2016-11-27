package storefront;

import java.util.HashMap;
import java.util.Map;

import eCommerce.InventoryModel;

public class ShoppingCart {
	private Map<String, ShoppingCartItem> shoppingCartItemMap = new HashMap<>();

	void add(InventoryModel inventoryModel) {
		if (inventoryModel != null) {
			if (shoppingCartItemMap.containsKey(inventoryModel.getId())) {
				shoppingCartItemMap.get(inventoryModel.getId()).incrementQuantity();
			} else {
				shoppingCartItemMap.put(inventoryModel.getId(), new ShoppingCartItem(inventoryModel));
			}
		}
	}

	public ShoppingCartItem getShoppingCartItem(String id) {
		return shoppingCartItemMap.get(id);
	}

	public boolean isEmpty() {
		return shoppingCartItemMap.isEmpty();
	}

	public int getItemCount() {
		int itemCount = 0;
		for (ShoppingCartItem shoppingCartItem : shoppingCartItemMap.values()) {
			itemCount += shoppingCartItem.getQuantityOrdered();
		}
		return itemCount;
	}
}
