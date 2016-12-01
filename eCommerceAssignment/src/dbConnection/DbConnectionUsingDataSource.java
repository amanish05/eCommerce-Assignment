package dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DbConnectionUsingDataSource {
	
	private static final BasicDataSource dataSource = new BasicDataSource();	
	static {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://cs3.calstatela.edu/cs3220stu40");
		dataSource.setUsername("cs3220stu40");
		dataSource.setPassword(".jTAsKQ0");
	}

	private DbConnectionUsingDataSource() {
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
