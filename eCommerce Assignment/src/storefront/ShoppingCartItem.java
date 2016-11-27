package storefront;

import eCommerce.InventoryModel;

public class ShoppingCartItem {
	private InventoryModel inventoryModel;
	private int quantityOrdered = 0;

	public ShoppingCartItem(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
		this.quantityOrdered = 1;
	}

	public InventoryModel getInventoryModel() {
		return inventoryModel;
	}

	public boolean incrementQuantity() {
		if (quantityOrdered < inventoryModel.getQuantity()) {
			quantityOrdered++;
			return true;
		}
		return false;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}
}
