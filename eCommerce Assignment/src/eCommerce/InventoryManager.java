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

@WebServlet("/Inventory")
public class InventoryManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private Connection connection;
    
    public InventoryManager() {
        super();        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResultSet rs;
		String sql =  "Select i.INVENTORY_PGUID, i.INVENTORY_NAME, i.INVENTORY_DESC, i.INVENTORY_PRICE_PER_UNIT, i.AVAILABLE_QUANTITY from inventory i";				
		List<InventoryModel> inventories = null;
		
		try{			
			connection = DBConnection.getConnection();			
			PreparedStatement pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();			
			
			inventories = new ArrayList<>(rs.getFetchSize());			
			while(rs.next()){
				inventories.add(InventoryModel.getInitiated(rs.getString("INVENTORY_PGUID"), rs.getString("INVENTORY_NAME"), 
						rs.getString("INVENTORY_DESC"), rs.getInt("AVAILABLE_QUANTITY"), rs.getFloat("INVENTORY_PRICE_PER_UNIT")));
			}
			
		}catch(Exception e){
			System.out.println("Exception while data request. Error: " +e.getMessage());
		}finally{
			DBConnection.closeConnection(connection);
		}
		
		request.setAttribute("inventories", inventories);
		request.getRequestDispatcher("/WEB-INF/Inventory/Inventory.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isError = false;
		
		String name = request.getParameter("name");
		if(!SystemUtil.validation(name)){
			isError = true;
			request.setAttribute("NameError", true);
		}
		
		String desc = request.getParameter("description");		
		String quantity = request.getParameter("quantity");
		int quan = 0 ;
		try{			
			quan = Integer.parseInt(quantity);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("QuantityError", true);
		}	
		
		String price = request.getParameter("price");
		float charge = 0;
		try{			
			charge =Float.parseFloat(price);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("priceError", true);
		}		
		
		System.out.println("InventoryManager: doPost Executing");
		if(!isError){
			
			String sql = "insert into inventory(INVENTORY_PGUID, INVENTORY_NAME, INVENTORY_DESC,INVENTORY_PRICE_PER_UNIT,AVAILABLE_QUANTITY, CREATED_DATETIME) values (?, ?, ?,?,?,now())";
			try{
				
				connection = DBConnection.getConnection();			
				PreparedStatement pstmt = connection.prepareStatement(sql);			
				pstmt.setString(1, SystemUtil.getUUID());		
				pstmt.setString(2, name);
				pstmt.setString(3, desc);
				pstmt.setInt(5, quan);
				pstmt.setFloat(4, charge);				
				
				pstmt.execute();
				doGet(request, response);
				
			}catch(Exception e){
				System.out.println("Exception while data insertion. Error: " +e.getMessage());
			}finally{
				DBConnection.closeConnection(connection);
			}
			
		}else{
			System.out.println("InventoryManager: doPost transfrring ");
			request.getRequestDispatcher("/WEB-INF/Inventory/AddInventory.jsp").forward(request, response);
		}			
	}
}
