package net.ertele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DaoCon {

	public static Connection getConnection() {
		Connection con = null;

	    try {
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/readitlater", "root", "");

	      if(!con.isClosed())
	        System.out.println("Successfully connected to " +
	          "MySQL server using TCP/IP...");

	    } catch(Exception e) {
	      System.err.println("Exception: " + e.getMessage());
	    }
		
		return con;
	}
}
