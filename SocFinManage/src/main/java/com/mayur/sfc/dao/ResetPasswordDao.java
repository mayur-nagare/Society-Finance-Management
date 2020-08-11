package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.ResetPasswordModel;

public class ResetPasswordDao {
	protected Connection conn;
	
	public boolean ChangePassword(MemberModel mem) throws SQLException{
		
		boolean passwordChanged;
		try(Connection conn= ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement("update society set password=? where emailId=?");){
			st.setString(1, mem.getPassword());
			st.setString(2, mem.getEmailId());
			System.out.println("Password Changed");
			
			passwordChanged = st.executeUpdate() > 0;
		}
		return passwordChanged;
	}
	
	public ArrayList<Object> checkMail(String email)throws SQLException{
		MemberModel mem = null;
		Connection conn = null;
		ResultSet rs =null;
		PreparedStatement st = null;
		ArrayList<Object> list = new ArrayList<>();
		try{
			conn= ConnectionDb.getConnection();
			st = conn.prepareStatement("select houseId, memName, emailId from society where emailId= ?");
			st.setString(1, email);
			rs = st.executeQuery();
			while(rs.next()) {
				long houseId = rs.getLong("houseId");
				String name = rs.getString("memName");
				String emailId = rs.getString("emailId");
				
				mem = new MemberModel(name, emailId, houseId);
			}
			
			Calendar cal = Calendar.getInstance();
		    long eTime = cal.getTimeInMillis();
		    eTime = eTime + 5*60*1000;
		    String expTime = String.valueOf(eTime);
		    
		    Random r = new Random();
		    long rand = 100000 + (long)(r.nextFloat() * 899900);
		    
		    st = conn.prepareStatement("insert into resetPass(token, emailId, tExp_time) values(?,?,?)");
		    st.setLong(1, rand);
		    st.setString(2, email);
		    st.setString(3, expTime);
		    
		    st.executeUpdate();
		    
		    ResetPasswordModel resetPasswordModel = new ResetPasswordModel(rand, email, expTime);
		    list.add(mem);
		    list.add(resetPasswordModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ResetPasswordModel getExpTime(long token, String mail)throws SQLException{
		
		ResetPasswordModel resPassword = null;
		Connection conn = null;
		ResultSet rs =null;
		PreparedStatement st = null;
		try{			
			conn= ConnectionDb.getConnection();
			st = conn.prepareStatement("select tExp_time from resetPass where token= ? and emailId= ?");
			st.setLong(1, token);
			st.setString(2, mail);
			rs = st.executeQuery();
			if(rs.next()) {
				
				String exptime = rs.getString("tExp_time");
				
				resPassword= new ResetPasswordModel(exptime);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return resPassword;
	}
}
