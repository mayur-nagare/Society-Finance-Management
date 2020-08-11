package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.SendMailModel;
import com.mayur.sfc.model.paymentModel;

public class PayBillDao {
	protected Connection conn;
	
	public String payBill(SendMailModel mem)throws SQLException{
		String message;
		//SendMailModel mem = null;
		PreparedStatement st = null;
		ResultSet rs = null;		
		long bal = mem.getAmount();
		
		try{
			Connection conn = ConnectionDb.getConnection();
			
			String mail = "";
			String name = "";
			long houseid = 0;
			String month = "";
			long year = 0;
			long amount = 0;
			
			long id = 0;
			long inId = 0;
			long BankBalance = 0;

			st = conn.prepareStatement("select max(Id) from BankDetail where ac_no= '091000019'");
			rs = st.executeQuery();
			if(rs.next()){
				id = rs.getLong("max(Id)");
			}
			
			st = conn.prepareStatement("select balance from BankDetail where Id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				BankBalance = rs.getLong("balance");
			}

			BankBalance = BankBalance + bal;
			
			st = conn.prepareStatement("select max(Id) from BankDetail");
			rs = st.executeQuery();
			while(rs.next()){
				inId = rs.getLong("max(Id)");
			}
			
			st = conn.prepareStatement("select amount from billpayment where houseId = ?");
			long balance = 0;
			long remainBal = 0;
			st.setLong(1, mem.getHouseId());
			rs = st.executeQuery();

			while(rs.next()){
				balance = rs.getLong("amount");
			}
			System.out.println("before cond "+balance);
			if((balance - bal) > 0 ) {
				 
				remainBal = balance - bal;
				System.out.println("in if balance="+balance+"\n remainingBal="+remainBal+"\n Bal="+bal);
				st = conn.prepareStatement("UPDATE billpayment set status='partially done', amount = "+remainBal+" where houseId= ? and month= ? and year= ?");
				st.setLong(1, mem.getHouseId());
				st.setString(2, mem.getMonth());
				st.setLong(3, mem.getYear());
				
				st.executeUpdate();
				
					
				st = conn.prepareStatement("select s.houseId, s.memName, s.emailId, b.month, b.year, b.amount from society s join billpayment b on s.houseId = b.houseId where s.houseId=? and month = ? and year = ?");
				st.setLong(1, mem.getHouseId());
				st.setString(2, mem.getMonth());
				st.setLong(3, mem.getYear());
				rs = st.executeQuery();
				while(rs.next()){
					houseid = rs.getLong("houseId");
					name = rs.getString("memName");
					mail = rs.getString("emailId");
					month = rs.getString("month");
					year = rs.getLong("year");
					amount = rs.getLong("amount");
					
				}
					
				st = conn.prepareStatement("insert into BankDetail(Id, ac_no, ac_name, credit_amt, credit_from_name, debit_amt, debit_to_name, balance, ct_dt_month, ct_dt_year, credit, debit) values(?,?,?,?,?,?,?,?,?,?,?,?)");

				st.setLong(1, ++inId);
				st.setString(2, "091000019");
				st.setString(3, "society");
				st.setLong(4, bal);
				st.setString(5, name);
				st.setLong(6, 0);
				st.setString(7, "");
				st.setLong(8, BankBalance);
				st.setString(9, month);
				st.setLong(10, year);
				st.setBoolean(11, true);
				st.setBoolean(12, false);

				st.executeUpdate();
					
				final String username= "bxxxxxr@gmail.com";
				final String password = "xxxxx";
				String subject = "Partial Bill Payment";
				String body = "Hii ("+ houseid +")"+ name +". Your Bill Payment "+ bal +" for "+ month +", "+ year +" is Successful. The remaining amount is "+ amount;
				
				sendFromGmail(username, password, mail, subject, body);
					
				return "Paid Partially";
					
			}else if((balance - bal) <= 0){
				remainBal = balance - bal;
				System.out.println("in if balance="+balance+"\n remainingBal="+remainBal+"\n Bal="+bal);
				st = conn.prepareStatement("UPDATE billpayment set status='done', amount = "+remainBal+" where houseId= ? and month= ? and year= ?");
				st.setLong(1, mem.getHouseId());
				st.setString(2, mem.getMonth());
				st.setLong(3, mem.getYear());
				st.executeUpdate();
				
				st = conn.prepareStatement("select s.houseId, s.memName, s.emailId, b.month, b.year, b.amount from society s join billpayment b on s.houseId = b.houseId where s.houseId=? and month = ? and year = ?");
				st.setLong(1, mem.getHouseId());
				st.setString(2, mem.getMonth());
				st.setLong(3, mem.getYear());
				rs = st.executeQuery();
				while(rs.next()){
					houseid = rs.getLong("houseId");
					name = rs.getString("memName");
					mail = rs.getString("emailId");
					month = rs.getString("month");
					year = rs.getLong("year");
					amount = rs.getLong("amount");
				}
				
				st = conn.prepareStatement("insert into BankDetail(Id, ac_no, ac_name, credit_amt, credit_from_name, debit_amt, debit_to_name, balance, ct_dt_month, ct_dt_year, credit, debit) values(?,?,?,?,?,?,?,?,?,?,?,?)");

				st.setLong(1, ++inId);
				st.setString(2, "091000019");
				st.setString(3, "society");
				st.setLong(4, bal);
				st.setString(5, name);
				st.setLong(6, 0);
				st.setString(7, "");
				st.setLong(8, BankBalance);
				st.setString(9, month);
				st.setLong(10, year);
				st.setBoolean(11, true);
				st.setBoolean(12, false);
				
				st.executeUpdate();
				
				final String username= "bxxxxxr@gmail.com";
				final String password = "xxxxx";
				String subject = "Full Bill Payment";
				String body = "Hii ("+ houseid +")"+ name +". Your Bill Payment "+ bal +" for "+ month +", "+ year +" is Successful. The remaining amount is "+ amount;
				
				sendFromGmail(username, password, mail, subject, body);
				
				return "Paid Fully";
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
			 
			 return "Payment Unsuccessfull";
	}
	
	private static void sendFromGmail(final String username, final String password, String emailId, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(username, password);
		                    }
		                });
		
		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bxxxxxr@gmail.com"));		
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId));
			
			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.send(message);
			transport.close();
			System.out.println("success");
		}catch(AddressException ae){
			ae.printStackTrace();
		}catch(MessagingException me){
			me.printStackTrace();
		}
	}
	
	
	public paymentModel selectPayer(long houseId, String sMonth, String sYear)throws SQLException{
		paymentModel payer = null;
		ResultSet rs= null;
		
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement st = conn.prepareStatement("select * from billpayment where houseId = ? and month= ? and year= ?");){
			
			st.setLong(1, houseId);
			st.setString(2, sMonth);
			st.setString(3, sYear);
			rs = st.executeQuery();
			while(rs.next()) {
				long id = rs.getLong("Id");
				long houseid = rs.getLong("houseId");
				String month = rs.getString("month");
				long year = rs.getLong("year");
				String status = rs.getString("status");
				long wcharge = rs.getLong("wcharge");
				long mcharge = rs.getLong("mcharge");
				long echarge = rs.getLong("echarge");
				long amount = rs.getLong("amount");
				
				payer = new paymentModel(id, houseid, month, year, status, wcharge, mcharge, echarge, amount);
			}
			

		}catch(SQLException e) {
			
		}
		return payer;
	}
	
	
	public int mainBill(paymentModel maintenance)throws SQLException{

		int mCostUpdate = 0;
		long id = 0;
		Connection conn = ConnectionDb.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try{
			
			st = conn.prepareStatement("select max(Id) from billpayment");
			rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getLong("max(Id)");
			}
			
			
			st= conn.prepareStatement("insert into billpayment(Id, houseId, month, year, status, wcharge, mcharge, echarge, amount, fine) values(?,?,?,?,?,?,?,?,?,?)");
			for(Long i : new SocietyDao().houseid() ) {
				st.setLong(1, ++id);
				st.setLong(2, i);
				st.setString(3, maintenance.getMonth());
				st.setLong(4, maintenance.getYear());
				st.setString(5, "not done");
				st.setLong(6, maintenance.getWcharge());
				st.setLong(7, maintenance.getMcharge());
				st.setLong(8, maintenance.getEcharge());
				st.setLong(9, maintenance.getAmount());
				st.setBoolean(10, false);
				
	
				mCostUpdate = st.executeUpdate();
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return mCostUpdate;
	}
	
	public void addFine() {
		paymentModel fine = null;
//		List<String> list = new ArrayList<>();
		
		Connection conn = ConnectionDb.getConnection();
		PreparedStatement st = null;
		ResultSet rs= null;
		
		try {
//			st = conn.prepareStatement("select status from billpayment where month= ? and year= ?");
//			st.setString(1, "Jan");
//			st.setLong(2, 2020);
//			rs = st.executeQuery();
//			
//			while(rs.next()) {
//				list.add(rs.getString("status"));
//			}
//			for(String i : list) {
//				if(i.equalsIgnoreCase("not done") || i.equalsIgnoreCase("partially done")) {
					
					st= conn.prepareStatement("update billpayment set fine= true,  amount= amount + 100 where month= ? and year= ? and status='not done'");
					st.setString(1, "Feb");
					st.setLong(2, 2020);
					st.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}
