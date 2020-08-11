package com.mayur.sfc.model;

public class MemberModel {
	
	private long houseId;
	private String memName;
	private String emailId;
	private long contact;
	private String username;
	private String password;
	private String role;
	
	public MemberModel() {
	}
	
	public MemberModel(long houseId) {
		this.houseId= houseId;
	}
	
	public MemberModel(String emailId) {
		this.emailId= emailId;
	}
	
	public MemberModel(String emailId, String password) {
		this.password = password;
		this.emailId = emailId;
	}
	
	public MemberModel(String memName, String emailId, long houseId) {
		this.emailId = emailId;
		this.memName = memName;
		this.houseId = houseId;
	}

	public MemberModel(long houseId, String password) {
		this.password = password;
		this.houseId = houseId;
	}
	
	public MemberModel(long houseid, String memName, String emailId, long contact,String role) {
		this.houseId = houseid;
		this.memName = memName;
		this.emailId = emailId;
		this.contact = contact;
		this.role = role;
	}
	
	public MemberModel(long houseid, String memName, String emailId, long contact, String password, String role) {
		this.houseId = houseid;
		this.memName = memName;
		this.emailId = emailId;
		this.contact = contact;
		this.password = password;
		this.role = role;
	}
	
	public MemberModel(String memName, String emailId, long contact, String password, String role) {
		this.memName = memName;
		this.emailId = emailId;
		this.contact = contact;
		this.password = password;
		this.role = role;
		
	}
	
	public long getHouseId() {
		return houseId;
	}
	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
//	@Override
//	public String toString() {
//		return "MemberModel [houseId=" + houseId + ", memName=" + memName + ", emailId=" + emailId + ", contact="
//				+ contact + "]";
//	}
	
	

}
