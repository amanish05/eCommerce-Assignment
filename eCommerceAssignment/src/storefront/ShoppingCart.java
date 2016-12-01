package storefront;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import eCommerce.InventoryModel;

public class ShoppingCart {
	private static final DecimalFormat df = new DecimalFormat("#.##");
	private Map<String, ShoppingCartItem> shoppingCartItemMap = new LinkedHashMap<>();

	boolean add(InventoryModel inventoryModel) {
		if (inventoryModel != null) {
			if (shoppingCartItemMap.containsKey(inventoryModel.getId())) {
				return shoppingCartItemMap.get(inventoryModel.getId()).incrementQuantity();
			} else {
				shoppingCartItemMap.put(inventoryModel.getId(), new ShoppingCartItem(inventoryModel));
				return true;
			}
		}
		return false;
	}

	public Collection<ShoppingCartItem> getItemList() {
		return shoppingCartItemMap.values();
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

	public float getTotalPrice() {
		float totalPrice = 0;
		for (ShoppingCartItem cartItem : shoppingCartItemMap.values()) {
			totalPrice += cartItem.getTotalPrice();
		}
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		return Float.valueOf(df.format(totalPrice));
	}

	public void reduceItemQuantity(ShoppingCartItem cartItem) {
		cartItem.decrementQuantity();
		if (cartItem.getQuantityOrdered() == 0) {
			shoppingCartItemMap.remove(cartItem.getItem().getId());
		}
	}

	public void deleteItem(ShoppingCartItem cartItem) {
		shoppingCartItemMap.remove(cartItem.getItem().getId());
	}
}
