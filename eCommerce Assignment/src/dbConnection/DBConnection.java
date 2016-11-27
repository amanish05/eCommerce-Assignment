package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;

public final class DBConnection {
	
	public static enum DBName {
		MANI, MANISH, TEE;
	}
	private static DBConnection dbconnection;

	// Mani
	private static final String MANI_DB_URL = "jdbc:mysql://cs3.calstatela.edu/cs3220stu32";
	private static final String MANI_DB_USER = "cs3220stu32";
	private static final String MANI_DB_PASS = "#lu6.##Z";
	// Manish
	private static final String MANISH_DB_URL = "jdbc:mysql://cs3.calstatela.edu/cs3220stu40";
	private static final String MANISH_DB_USER = "cs3220stu40";
	private static final String MANISH_DB_PASS = ".jTAsKQ0";
	// Tee
	private static final String TEE_DB_URL = "";
	private static final String TEE_DB_USER = "";
	private static final String TEE_DB_PASS = "";
	
	private DBConnection() throws ServletException{
		loadDriver();
	}

	public static Connection getConnection() throws ServletException {
		return getConnection(DBName.MANISH);
	}

	public static Connection getConnection(DBName dbName) throws ServletException {
		Connection c = null;
		try {
			if(dbconnection == null){
				dbconnection = new DBConnection();
			}
			if (dbName == DBName.MANI) {
				c = DriverManager.getConnection(MANI_DB_URL, MANI_DB_USER, MANI_DB_PASS);
			} else if (dbName == DBName.MANISH) {
				c = DriverManager.getConnection(MANISH_DB_URL, MANISH_DB_USER, MANISH_DB_PASS);
			} else if (dbName == DBName.TEE) {
				c = DriverManager.getConnection(TEE_DB_URL, TEE_DB_USER, TEE_DB_PASS);
			}
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
