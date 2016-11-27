package eCommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnection.DBConnection;

@WebServlet("/Update")
public class UpdateInventory extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateInventory() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection connection = null;
		InventoryModel model = null;
		String sql = "Select i.INVENTORY_PGUID, i.INVENTORY_NAME, i.INVENTORY_DESC, i.AVAILABLE_QUANTITY, i.INVENTORY_PRICE_PER_UNIT from inventory i where i.INVENTORY_PGUID = ?";
		
		try{
			connection = DBConnection.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("id"));
			
			ResultSet rs = pstmt.executeQuery();			
			
			while(rs.next()){
				model = new InventoryModel();
				model.setId(rs.getString("INVENTORY_PGUID"));
				model.setName(rs.getString("INVENTORY_NAME"));
				model.setDescription(rs.getString("INVENTORY_DESC"));
				model.setPrice(rs.getFloat("INVENTORY_PRICE_PER_UNIT"));
				model.setQuantity(rs.getInt("AVAILABLE_QUANTITY"));				
			}
				
		}catch(Exception e){
			System.out.println("Exception while Archiving state. Error: " +e.getMessage());			
		}finally{
			DBConnection.closeConnection(connection);
		}		
		request.setAttribute("inventory", model);
		request.getRequestDispatcher("/WEB-INF/Inventory/AddInventory.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Connection connection = null;
		InventoryModel model = null;
		String sql = "Update inventory set INVENTORY_NAME =?, INVENTORY_DESC =?, AVAILABLE_QUANTITY =?, INVENTORY_PRICE_PER_UNIT=? where INVENTORY_PGUID = ?";
		
		boolean isError = false;
		
		String name = request.getParameter("name");
		if(!SystemUtil.validation(name)){
			isError = true;
			request.setAttribute("NameError", true);
		}
		
		String desc = request.getParameter("description");		
		String quantity = request.getParameter("quantity");
		int quan = 0;
		try{			
			quan = Integer.parseInt(quantity);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("QuantityError", true);
		}	
		
		String price = request.getParameter("price");
		float charge = 0;
		try{			
			charge = Float.parseFloat(price);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("priceError", true);
		}		
		
		if(!isError){
			
			try{				
				connection = DBConnection.getConnection();			
				PreparedStatement pstmt = connection.prepareStatement(sql);						
				pstmt.setString(1, name);
				pstmt.setString(2, desc);
				pstmt.setInt(3, quan);
				pstmt.setFloat(4, charge);
				pstmt.setString(5, request.getParameter("id"));
				
				pstmt.executeUpdate();
				response.sendRedirect("Inventory");
				
			}catch(Exception e){
				System.out.println("UpdateInventory:doPost, Exception while Updating data. Error: " +e.getMessage());
			}finally{
				DBConnection.closeConnection(connection);
			}
			
		}else{
			request.getRequestDispatcher("/WEB-INF/Inventory/AddInventory.jsp").forward(request, response);
		}
			
	}
}
