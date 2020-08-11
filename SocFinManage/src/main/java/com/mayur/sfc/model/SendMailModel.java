package com.mayur.sfc.model;

public class SendMailModel {

	private long houseId;
	private String memName;
	private String emailId;
	private long contact;
	private String month;
	private long year;
	private String status;
	private long amount;
	private boolean fine;
	
	public SendMailModel() {
		
	}
	
	public SendMailModel(long houseId, String month, long year, long amount) {
		this.houseId = houseId;
		this.month = month;
		this.year = year;
		this.amount = amount;
	}
	
	public SendMailModel(long houseId, String memName, String emailId, long contact, String month, long year, String status, long amount, boolean fine) {
		this.houseId = houseId;
		this.memName = memName;
		this.emailId = emailId;
		this.contact = contact;
		this.month = month;
		this.year = year;
		this.status = status;
		this.amount = amount;
		this.fine = fine;
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

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public boolean isFine() {
		return fine;
	}

	public void setFine(boolean fine) {
		this.fine = fine;
	}


}
