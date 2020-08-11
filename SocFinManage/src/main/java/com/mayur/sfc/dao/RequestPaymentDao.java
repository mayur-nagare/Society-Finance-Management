package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.SocietyWorkerModel;
import com.mayur.sfc.model.WorkerPaymentModel;

public class RequestPaymentDao {

	protected Connection conn;
	
	public boolean sendPaymentRequest(long houseId, String memName, String emailId, String month, long year, long amount)throws SQLException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionDb.getConnection();
			
			long id = 0;
			long houseIdDB = 0;
			String memNameDB = "";
			String emailIdDB = "";
			
			st = conn.prepareStatement("select houseId, memName, emailId from society where houseId = ?");
			st.setLong(1, houseId);

			
			rs = st.executeQuery();
			while(rs.next()) {
				houseIdDB = rs.getLong("houseId");
				memNameDB = rs.getString("memName");
				emailIdDB = rs.getString("emailId");
			}
			
			st = conn.prepareStatement("select max(Id) from WorkerPayment");
			rs = st.executeQuery();
			while(rs.next()){
				id = rs.getLong("max(Id)");
			}
			
			st = conn.prepareStatement("insert into WorkerPayment(Id, houseId, month, year, amount, status) values(?,?,?,?,?,?)");
			st.setLong(1, ++id);
			st.setLong(2, houseId);
			st.setString(3, month);
			st.setLong(4, year);
			st.setLong(5, amount);
			st.setString(6, "pending");
			
			st.executeUpdate();
			if(houseIdDB == houseId && memNameDB.equals(memName) && emailIdDB.equals(emailId)) {
				return true;
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public List<SocietyWorkerModel> showWorkerPendingList(){
		SocietyWorkerModel worker = null;
		List<SocietyWorkerModel> list = new ArrayList<>();
		ResultSet rs= null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement("select s.houseId, s.memName, s.emailId, w.month, w.year, w.amount, w.status from society s join WorkerPayment w on s.houseId = w.houseId where status= ?");){

				preparedst.setString(1, "pending");
			rs = preparedst.executeQuery();
			
			while(rs.next()) {
				worker = new SocietyWorkerModel();
				worker.setHouseId(rs.getLong("houseId"));
				worker.setMemName(rs.getString("memName"));
				worker.setEmailId(rs.getString("emailId"));
				worker.setMonth(rs.getString("month"));
				worker.setYear(rs.getLong("year"));
				worker.setAmount(rs.getLong("amount"));
				worker.setStatus(rs.getString("status"));
				
				list.add(worker);
			}
		}catch(Exception e) {
			
		}
		return list;
	}
	
	public boolean WorkerRequestApproval(long houseId)throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionDb.getConnection();
			
			long wId = 0;
			long houseid = 0;
			String name = "";
			String mail = "";
			String month = "";
			long year = 0;
			long amount = 0;
			String status = "";
			
			st = conn.prepareStatement("select max(Id) from WorkerPayment where houseId = ?");
			st.setLong(1, houseId);
			
			rs = st.executeQuery();
			while(rs.next()) {
				wId = rs.getLong("max(Id)"); 
			}
			
			st = conn.prepareStatement("select s.houseId, s.memName, s.emailId, w.month, w.year, w.amount, w.status from society s join WorkerPayment w on s.houseId = w.houseId where Id = ? and s.houseId= ? and status= ?");
			st.setLong(1, wId);
			st.setLong(2, houseId);
			st.setString(3, "pending");
			
			rs = st.executeQuery();
			while(rs.next()) {
				houseid = rs.getLong("houseId");
				name = rs.getString("memName");
				mail = rs.getString("emailId");
				month = rs.getString("month");
				year = rs.getLong("year");
				amount = rs.getLong("amount");
				status = rs.getString("status");
			}
			
			st = conn.prepareStatement("update WorkerPayment set status = 'paid' where Id = ? and houseId= ? and status= ?");
			st.setLong(1, wId);
			st.setLong(2, houseid);
			st.setString(3, status);
			
			st.executeUpdate();
			
			long id = 0;
			long inId = 0;
			long BankBalance = 0;
			
			st = conn.prepareStatement("select max(Id) from BankDetail where ac_no= '091000019'");
			rs = st.executeQuery();
			while(rs.next()){
				id = rs.getLong("max(Id)");
			}
			
			st = conn.prepareStatement("select max(Id) from BankDetail");
			rs = st.executeQuery();
			while(rs.next()){
				inId = rs.getLong("max(Id)");
			}
			
			st = conn.prepareStatement("select balance from BankDetail where Id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				BankBalance = rs.getLong("balance");
			}
		
			if(BankBalance !=0 || (BankBalance - amount) >0) {
				BankBalance = BankBalance - amount;
				
				st = conn.prepareStatement("insert into BankDetail(Id, ac_no, ac_name, credit_amt, credit_from_name, debit_amt, debit_to_name, balance, ct_dt_month, ct_dt_year, credit, debit) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		
				st.setLong(1, ++inId);
				st.setString(2, "091000019");
				st.setString(3, "society");
				st.setLong(4, 0);
				st.setString(5, "");
				st.setLong(6, amount);
				st.setString(7, name);
				st.setLong(8, BankBalance);
				st.setString(9, month);
				st.setLong(10, year);
				st.setBoolean(11, false);
				st.setBoolean(12, true);
				
				st.executeUpdate();
			}
			
			final String username= "bxxxxxr@gmail.com";
			final String password = "xxxxx";
			String subject = "Worker Pending Payment";
			String body = "Hello, ("+ houseId +")"+ name +".\nYour Work Payment "+ amount +" for "+ month +", "+ year +" is Approved.\nWe sent you the amount.\nThank you.\nFrom, "+username;
			
			sendFromGmail(username, password, subject, body);
			
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static void sendFromGmail(final String username, final String password, String subject, String body) {
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
			message.addRecipient(Message.RecipientType.TO,new InternetAddress("bxxxxxr@gmail.com"));
			
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
}
