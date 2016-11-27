package eCommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnection.DBConnection;

@WebServlet("/Remove")
public class RemoveInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemoveInventory() {
        super();        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		String sql = "DELETE FROM inventory where INVENTORY_PGUID = ? LIMIT 1";
		try{
			connection = DBConnection.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("id"));
			
			pstmt.execute();			
				
		}catch(Exception e){
			System.out.println("Exception while Archiving state. Error: " +e.getMessage());			
		}finally{
			DBConnection.closeConnection(connection);
		}
		
		response.sendRedirect("Inventory");				
	}

}
