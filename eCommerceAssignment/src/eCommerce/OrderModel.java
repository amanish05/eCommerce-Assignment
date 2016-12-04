package eCommerce;

import java.util.List;

public class OrderModel {
	
	private String id;
	private List<InventoryModel> list;
	private String createdDate;
	private float orderTotal;
	
	public float getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getId() {
		return id;
	}
	public List<InventoryModel> getList() {
		return list;
	}
	public void setList(List<InventoryModel> list) {
		this.list = list;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public static InventoryModel getInitiated(String inv, float price,  int quan , float total){
		
		InventoryModel inventoryModel = new InventoryModel();
		//inventoryModel.setId(String.valueOf(id));
		//inventoryModel.setEmail(email);
		inventoryModel.setName(inv);
		inventoryModel.setQuantity(quan);
		inventoryModel.setPrice(price);
		inventoryModel.setTotal(total);
		
		
		return inventoryModel;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
