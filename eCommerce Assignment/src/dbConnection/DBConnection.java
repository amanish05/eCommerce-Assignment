package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;

public final class DBConnection {

	public static Connection getConnection() throws ServletException {

		Connection c = null;
		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu40";
			String username = "cs3220stu40";
			String password = ".jTAsKQ0";

			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		return c;

	}

	public static void closeConnection(Connection c) throws ServletException {

		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	public static void loadDriver() throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

}
