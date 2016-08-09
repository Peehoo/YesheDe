package yeshede;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import yeshede.dao.MysqlConnector;

public class Yeshede {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		System.out.println("Connection Successful");
		
		Statement stmt = conn.createStatement();
	    String sql = "select * from original_source";
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String name = rs.getString("name");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Name: " + name);
	      }
	}

}
