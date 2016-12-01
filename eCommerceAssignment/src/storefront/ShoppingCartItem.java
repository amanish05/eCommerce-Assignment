package storefront;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import eCommerce.InventoryModel;

public class ShoppingCartItem {
	private static final DecimalFormat df = new DecimalFormat("#.##");
	private InventoryModel inventoryModel;
	private int quantityOrdered = 0;

	public ShoppingCartItem(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
		this.quantityOrdered = 1;
	}

	public InventoryModel getItem() {
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

	public float getTotalPrice() {
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		return Float.valueOf(df.format(quantityOrdered * inventoryModel.getPrice()));
	}

	public void decrementQuantity() {
		if (quantityOrdered > 0) {
			quantityOrdered--;
		}
	}
}
