package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.BankDetailModel;

public class YearlySpendingDao {
	protected Connection conn;
	
	public List<BankDetailModel> getList(long year)throws SQLException{
		
		BankDetailModel detail = null;
		List<BankDetailModel> list = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionDb.getConnection();
			st = conn.prepareStatement("select ac_name, credit_amt, credit_from_name, debit_amt, debit_to_name, balance, ct_dt_month, ct_dt_year from BankDetail where ct_dt_year= ? order by ct_dt_month;");
			st.setLong(1, year);
			rs = st.executeQuery();
			while(rs.next()) {
				detail = new BankDetailModel();
				detail.setAc_name(rs.getString("ac_name"));
				detail.setCredit_amt(rs.getLong("credit_amt"));
				detail.setCredit_from_name(rs.getString("credit_from_name"));
				detail.setDebit_amt(rs.getLong("debit_amt"));
				detail.setDebit_to_name(rs.getString("debit_to_name"));
				detail.setBalance(rs.getLong("balance"));
				detail.setCt_dt_month(rs.getString("ct_dt_month"));
				detail.setCt_dt_year(rs.getLong("ct_dt_year"));
				
				list.add(detail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
