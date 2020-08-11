package com.mayur.sfc.model;

public class ResetPasswordModel {

	private long token;
	private String emailId;
	private String tExp_time;
	
	
	
	public ResetPasswordModel() {

	}
	
	public ResetPasswordModel(String tExp_time) {
		this.tExp_time = tExp_time;
	}
	public ResetPasswordModel(long token, String emailId) {
		this.token = token;
		this.emailId = emailId;
	}
	
	public ResetPasswordModel(long token, String emailId, String tExp_time) {
		this.token = token;
		this.emailId = emailId;
		this.tExp_time = tExp_time;
	}
	public long getToken() {
		return token;
	}
	public void setToken(long token) {
		this.token = token;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String gettExp_time() {
		return tExp_time;
	}
	public void settExp_time(String tExp_time) {
		this.tExp_time = tExp_time;
	}
	
	
 	}
