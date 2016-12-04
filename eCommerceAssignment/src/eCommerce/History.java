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

@WebServlet("/History")
public class History extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Inventory/history.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResultSet rs, os;
		List<InventoryModel> orderItems = null;
		List<OrderModel> orders = null;
		
		String email = request.getParameter("email");
		System.out.println("History: post method invoked");
		
		String sql =  "Select i.ORDER_NUMBER, i.ORDERED_DATETIME from inventory_transactions i where i.CUSTOMER_EMAIL = ? GROUP BY ORDER_NUMBER order by ORDERED_DATETIME DESC";		
		String oredrSql =  "Select i.CUSTOMER_EMAIL, i.INVENTORY_NAME, i.INVENTORY_PRICE_PER_UNIT, i.ORDERED_QUANTITY, i.AMOUNT from "
				+ "inventory_transactions i where ORDER_NUMBER = ?";
		
		try{
			if(email == null || email.trim().length() == 0){
				throw new RuntimeException("Email address can't be blank");
			}
			connection = DBConnection.getConnection();			
			PreparedStatement pstmt = connection.prepareStatement(sql);	
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();				
			
			orders = new ArrayList<>();			
			while(rs.next()){
				float totalPrice = 0;
				orderItems = new ArrayList<>();
				PreparedStatement stmt = connection.prepareStatement(oredrSql);
				stmt.setInt(1, rs.getInt("ORDER_NUMBER"));
				os = stmt.executeQuery();				
				System.out.println("No. of items in first orders is:" +os.getFetchSize());
				while(os.next()){
					orderItems.add(OrderModel.getInitiated(os.getString("INVENTORY_NAME"),os.getFloat("INVENTORY_PRICE_PER_UNIT"), os.getInt("ORDERED_QUANTITY"), 
							os.getFloat("AMOUNT")));
					totalPrice += os.getFloat("AMOUNT");
				}
				OrderModel om = new OrderModel();
				om.setId(rs.getString("ORDER_NUMBER"));
				om.setCreatedDate(rs.getString("ORDERED_DATETIME"));
				om.setList(orderItems);
				om.setOrderTotal(totalPrice);
				orders.add(om);
			}
			
		}catch(Exception e){
			System.out.println("Exception while data request. Error: " +e.getMessage());
		}finally{
			DBConnection.closeConnection(connection);
		}
		
		request.setAttribute("orderItems", orders);		
		doGet(request, response);
	}
}
