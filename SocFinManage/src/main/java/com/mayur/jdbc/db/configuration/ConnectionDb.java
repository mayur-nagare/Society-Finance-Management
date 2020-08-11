package com.mayur.jdbc.db.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectionDb {
	public static final String DATABASENAME = "sfc";
	public static final String DATABASEURL = "jdbc:mysql://localhost:3306/" + DATABASENAME;
	public static final String DATABASEUSERNAME = "root";
	public static final String DATABASEPASSWORD = "Test1234";

	private static ConnectionDb instance = new ConnectionDb();
	
	public static final HashMap<Integer, String> month = new HashMap<Integer, String>() {
		{
			put(1, "Jan");
			put(2, "Feb");
			put(3, "Mar");
			put(4, "Apr");
			put(5, "May");
			put(6, "June");
			put(7, "July");
			put(8, "Aug");
			put(9, "Sep");
			put(10, "Oct");
			put(11, "Nov");
			put(12, "Dec");
		}
	};
	
	public static final HashMap<String, Integer> monthrev = new HashMap<String, Integer>() {
		{
			put("Jan", 1);
			put("Feb", 2);
			put("Mar", 3);
			put("Apr", 4);
			put("May", 5);
			put("June", 6);
			put("July", 7);
			put("Aug", 8);
			put("Sep", 9);
			put("Oct", 10);
			put("Nov", 11);
			put("Dec", 12);
		}
	};
	private ConnectionDb() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public Connection createConn() {
		Connection conn = null;
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sfc?autoReconnect=true&useSSL=false","root","Test1234");
		}catch(SQLException e) {
			System.out.println("Error: Unable to connect to Database");
		}
		
		return conn;
	}
	
	public static Connection getConnection() {
		return instance.createConn();
	}
}


