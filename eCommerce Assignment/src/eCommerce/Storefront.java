package eCommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnection.DBConnection;

@WebServlet("/Store")
public class Storefront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
       
    public Storefront() {
        super();        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<InventoryModel>  inventory = null;		
		String sql = "Select inv.INVENTORY_PGUID, inv.INVENTORY_NAME, inv.INVENTORY_DESC, inv.INVENTORY_PRICE_PER_UNIT, inv.AVAILABLE_QUANTITY from "
				+ "inventory inv where inv.AVAILABLE_QUANTITY > 0";				
		
		try {
			connection = DBConnection.getConnection();
        	PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			inventory = new ArrayList<>();			
			
			while( rs.next() ){	             
				
				InventoryModel model = new InventoryModel();
				model.setId(rs.getString("INVENTORY_PGUID"));
				model.setName(rs.getString("INVENTORY_NAME"));
				model.setDescription(rs.getString("INVENTORY_DESC"));
				model.setPrice(rs.getFloat("INVENTORY_PRICE_PER_UNIT"));
				model.setQuantity(rs.getInt("AVAILABLE_QUANTITY"));				 		         
		         
		        inventory.add(model);	         
	        }	        
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(connection);
		}
		
		request.setAttribute("inventory", inventory);				
		request.getRequestDispatcher("/WEB-INF/Views/Inventory/Inventory.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
