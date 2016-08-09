package yeshede.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/yeshe_de";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "root";

	private static Connection conn;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		if (conn == null) {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		return conn;
	}
}
