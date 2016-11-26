package eCommerce;

import java.util.Date;

public class InventoryModel {
	
	private String id;
	private String name;
	private String description;
	private int quantity;
	private float price;
	private Date addedDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public static InventoryModel getInitiated(String id, String name, String desc, int quan, float price){
		
		InventoryModel inventoryModel = new InventoryModel();
		inventoryModel.setId(id);
		inventoryModel.setName(name);
		inventoryModel.setDescription(desc);
		inventoryModel.setQuantity(quan);
		inventoryModel.setPrice(price);
		inventoryModel.setAddedDate(new Date());
		
		return inventoryModel;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

}
