package eCommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

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
		try{			
			Integer.parseInt(quantity);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("QuantityError", true);
		}	
		
		String price = request.getParameter("price");
		try{			
			Float.parseFloat(price);
		}catch(NumberFormatException e){
			isError = true;
			request.setAttribute("priceError", true);
		}		
		
		if(!isError){
			
			String sql = "insert into inventory(INVENTORY_PGUID, INVENTORY_NAME, INVENTORY_DESC,INVENTORY_PRICE_PER_UNIT,AVAILABLE_QUANTITY, CREATED_DATETIME) values (?, ?, ?,?,?,?)";
			try{
				
				connection = DBConnection.getConnection();			
				PreparedStatement pstmt = connection.prepareStatement(sql);			
				pstmt.setString(1, SystemUtil.getUUID());		
				pstmt.setString(2, name);
				pstmt.setString(3, desc);
				pstmt.setString(4, quantity);
				pstmt.setString(5, price);
				pstmt.setString(6, new Date().toString());
				
				pstmt.execute();			
				
			}catch(Exception e){
				System.out.println("Exception while adding data. Error: " +e.getMessage());
			}finally{
				DBConnection.closeConnection(connection);
			}
			
		}else{
			request.getRequestDispatcher("/WEB-INF/Views/AddItem.jsp").forward(request, response);
		}			
	}
}
