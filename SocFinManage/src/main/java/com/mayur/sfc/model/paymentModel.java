package com.mayur.sfc.model;

public class paymentModel {

	private long Id;
	private long houseId;
	private String month;
	private long year;
	private String status;
	private String date;
	private long wcharge;
	private long mcharge;
	private long echarge;
	private long amount;
	private boolean fine;

	public paymentModel() {

	}
	
	public paymentModel(String date) {
		this.date = date;
	}
	
	public paymentModel(long houseId) {
		this.houseId = houseId;
	}
	public paymentModel(long houseId, String month, long year) {
		this.houseId = houseId;
		this.month = month;
		this.year = year;
	}
	
	public paymentModel(String month, long year, long wcharge, long mcharge, long echarge, long amount) {
		this.month = month;
		this.year = year;
		this.wcharge = wcharge;
		this.mcharge = mcharge;
		this.echarge = echarge;
		this.amount = amount;
	}
	
	public paymentModel(long id,long houseId, String month, long year) {
		this(houseId, month, year);
		this.Id = id;
	}
	
	public paymentModel(long houseId, String month, long year, long amount, String date) {
		this(houseId, month, year);
		this.amount = amount;
		this.date = date;
	}
	
	public paymentModel(long id,long houseId, String month, long year, String status) {
		this(id, houseId, month, year);
		this.status = status;
	}
	
	public paymentModel(long id, long houseId, String month, long year, String status, long wcharge, long mcharge, long echarge, long amount) {
		this(id, houseId, month, year);
		this.status = status;
		this.wcharge = wcharge;
		this.mcharge = mcharge;
		this.echarge = echarge;
		this.amount = amount;
	}
	
	public boolean isFine() {
		return fine;
	}

	public void setFine(boolean fine) {
		this.fine = fine;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getHouseId() {
		return houseId;
	}
	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
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

	public long getWcharge() {
		return wcharge;
	}

	public void setWcharge(long wcharge) {
		this.wcharge = wcharge;
	}

	public long getMcharge() {
		return mcharge;
	}

	public void setMcharge(long mcharge) {
		this.mcharge = mcharge;
	}

	public long getEcharge() {
		return echarge;
	}

	public void setEcharge(long echarge) {
		this.echarge = echarge;
	}

	
	
}
