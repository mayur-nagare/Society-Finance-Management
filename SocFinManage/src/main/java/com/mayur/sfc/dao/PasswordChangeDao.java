package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;

public class PasswordChangeDao {
		private Connection conn;
		public boolean ChangePassword(MemberModel mem) throws SQLException{
			
			boolean passwordChanged;
			try(Connection conn= ConnectionDb.getConnection();
					PreparedStatement st = conn.prepareStatement("update society set password=? where houseId=?;");){
				st.setString(1, mem.getPassword());
				st.setLong(2, mem.getHouseId());
				System.out.println("Password Changed");
				
				passwordChanged = st.executeUpdate() > 0;
			}
			return passwordChanged;
		}
	
}
