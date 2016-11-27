package eCommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbConnection.DBConnection;

@WebServlet("/Login")
public class InventoryLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private String error;	
       
    public InventoryLogin() {
        super();        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId 	= request.getParameter("userId");
		char[] passcode = request.getParameter("passcode").toCharArray();
		
		if(SystemUtil.validation(userId) && SystemUtil.validation(passcode)){
			
			connection = DBConnection.getConnection();
			String sql = "Select c.PASSCODE from  client c where c.USER_ID = ?";
			
			try{
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, userId);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.getFetchSize() == 1){
					
					byte[] hashedCode = rs.getBytes("PASSCODE");
					if(SystemUtil.isExpectedPassword(passcode, hashedCode)){
						
						if(request.getParameter("rememberMe")!= null && request.getParameter("rememberMe").equals("true")){							
							Cookie cookie  = new Cookie("username", userId);				
							response.addCookie(cookie);
						}												

						HttpSession session = request.getSession();						
						session.setAttribute("CurrentUser", userId);
						session.setMaxInactiveInterval(0);
						
						response.sendRedirect("Inventory");
					}else{
						error = "User id/Password is invalid. Please try again";
					}
				}else{
					error = "User id/Password is invalid. Please try again";
				}
				
			}catch(Exception e){				
				
			}			
		}else{
			error = "User id/Password is invalid. Please try again";
		}
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/Views/Login.jsp").forward(request, response);		
	}	
}
