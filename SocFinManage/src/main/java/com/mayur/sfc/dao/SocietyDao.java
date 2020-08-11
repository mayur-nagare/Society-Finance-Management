package com.mayur.sfc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;

public class SocietyDao {
	protected Connection conn;
	
	private static final String INSERT_USERS = "insert into society(houseId, memName, emailId, contact, password, role) values(?,?,?,?,?,?)";
	private static final String SELECT_USER_BY_ID = "select houseId, memName, emailId, contact, role from society where houseId =?";
	private static final String SELECT_ALL_USERS = "select * from society";
	private static final String DELETE_USERS_SQL = "delete from society where houseId = ?";
	private static final String UPDATE_USERS_SQL = "update society set memName = ?, emailId= ?, contact= ?, password= ?, role= ? where houseId = ?";
	
	
	public void insertMember(MemberModel member)throws SQLException{
		
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement(INSERT_USERS);){
			preparedst.setLong(1, member.getHouseId());
			preparedst.setString(2, member.getMemName());
			preparedst.setString(3, member.getEmailId());
			preparedst.setLong(4, member.getContact());
			preparedst.setString(5, member.getPassword());
			preparedst.setString(6, member.getRole());
			
			preparedst.executeUpdate();
		}catch(SQLException e) {
			
		}
	}
	
	
	public List<MemberModel> getAllMembers() {
		MemberModel member = null;
		List<MemberModel> list = new ArrayList<>();
		
		ResultSet rs= null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement(SELECT_ALL_USERS);){

			rs = preparedst.executeQuery();
			
			while(rs.next()) {
				member = new MemberModel();
				member.setHouseId(rs.getLong("houseId"));
				member.setMemName(rs.getString("memName"));
				member.setEmailId(rs.getString("emailId"));
				member.setContact(rs.getLong("contact"));
				
				list.add(member);
			}
		}catch(Exception e) {
			
		}
		return list;
	}
	
	public MemberModel selectMember(long houseId) {
		MemberModel member = null;
		ResultSet rs= null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement(SELECT_USER_BY_ID);){
			
			preparedst.setLong(1, houseId);
			rs = preparedst.executeQuery();
			
			while(rs.next()) {
				long houseid = rs.getLong("houseId");
				String memName = rs.getString("memName");
				String emailId = rs.getString("emailId");
				long contact = rs.getLong("contact");
				String role = rs.getString("role");
				
				member = new MemberModel(houseid, memName, emailId, contact, role);
			}
		}catch(SQLException e) {
			
		}
		return member;
	}
	
		
	public boolean updateMember(MemberModel member) throws SQLException{
		
		boolean rowUpdated;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement(UPDATE_USERS_SQL);){
			
			st.setString(1, member.getMemName());
			st.setString(2, member.getEmailId());
			st.setLong(3, member.getContact());
			st.setString(4, member.getPassword());
			st.setString(5, member.getRole());
			st.setLong(6, member.getHouseId());
			
			rowUpdated = st.executeUpdate() >0;
		}
		return rowUpdated;
	}
	
	public boolean deleteMember(long houseId) throws SQLException{
		boolean rowDeleted;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement(DELETE_USERS_SQL);){
			st.setLong(1, houseId);
			rowDeleted = st.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public List<Long> houseid(){
		
		ArrayList<Long> houseIdList = new ArrayList<>();
		ResultSet rs = null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement("select houseId from society where houseId < 999");){
			
			rs = st.executeQuery();
			while(rs.next()) {
				houseIdList.add(rs.getLong("houseId"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return houseIdList;
	}
}
