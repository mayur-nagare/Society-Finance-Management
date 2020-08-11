package com.mayur.sfc.model;

public class BankDetailModel {

	private long Id;
	private String ac_no;
	private String ac_name;
	private long credit_amt;
	private String credit_from_name;
	private long debit_amt;
	private String debit_to_name;
	private long balance;
	private String ct_dt_month;
	private long ct_dt_year;
	private boolean credit;
	private boolean dedit;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		this.Id = id;
	}
	public String getAc_no() {
		return ac_no;
	}
	public void setAc_no(String ac_no) {
		this.ac_no = ac_no;
	}
	public String getAc_name() {
		return ac_name;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	public long getCredit_amt() {
		return credit_amt;
	}
	public void setCredit_amt(long credit_amt) {
		this.credit_amt = credit_amt;
	}
	public String getCredit_from_name() {
		return credit_from_name;
	}
	public void setCredit_from_name(String credit_from_name) {
		this.credit_from_name = credit_from_name;
	}
	public long getDebit_amt() {
		return debit_amt;
	}
	public void setDebit_amt(long debit_amt) {
		this.debit_amt = debit_amt;
	}
	public String getDebit_to_name() {
		return debit_to_name;
	}
	public void setDebit_to_name(String debit_to_name) {
		this.debit_to_name = debit_to_name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getCt_dt_month() {
		return ct_dt_month;
	}
	public void setCt_dt_month(String ct_dt_month) {
		this.ct_dt_month = ct_dt_month;
	}
	public long getCt_dt_year() {
		return ct_dt_year;
	}
	public void setCt_dt_year(long ct_dt_year) {
		this.ct_dt_year = ct_dt_year;
	}
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	public boolean isDedit() {
		return dedit;
	}
	public void setDedit(boolean dedit) {
		this.dedit = dedit;
	}
	
}
