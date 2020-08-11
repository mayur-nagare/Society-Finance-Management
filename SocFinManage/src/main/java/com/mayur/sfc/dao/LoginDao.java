package com.mayur.sfc.dao;

import java.sql.*;

import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;

public class LoginDao {
	protected Connection conn;
	
	private static final String SELECT_USER = "select houseId, password, role from society where houseId = ?";
	
	
	public String userAuthenticate(MemberModel loginbean) {

		long houseId = loginbean.getHouseId();
		String password = loginbean.getPassword();
		
		ResultSet rs = null;
		
		String passwordDb = "";
		String roleDb = "";
		long houseID = 0;
		
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement(SELECT_USER);){
			
			st.setLong(1, loginbean.getHouseId());
			rs = st.executeQuery();
			
			while(rs.next()) {
				houseID = rs.getLong("houseId");
				passwordDb = rs.getString("password");
				roleDb = rs.getString("role");
				
				if(houseId == houseID && password.equals(passwordDb) && roleDb.equalsIgnoreCase("admin"))
					return "Admin_Role";
				else if(houseId == houseID && password.equals(passwordDb) && roleDb.equalsIgnoreCase("user"))
					return "User_Role";
				else if(houseId == houseID && password.equals(passwordDb) && roleDb.equalsIgnoreCase("worker"))
					return "Worker_Role";
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return "Invalid User Credentials";
	
	}
}
