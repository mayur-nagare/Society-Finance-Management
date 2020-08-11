package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
import com.mayur.sfc.model.SendMailModel;

public class SendMailDao {

	private static final String SELECT_ALL_USERS = "select s.houseId, s.memName, s.emailId, s.contact, b.month, b.year, b.status, b.amount, b.fine from society s join billpayment b on s.houseId = b.houseId where month = ? and year = ?";
	private static final String mail_user ="select email from society where houseId = 101";
	
	public void getAllMembers() {
		SendMailModel member = null;
		
		List<SendMailModel> list = new ArrayList<>();
		
		ResultSet rs= null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement(SELECT_ALL_USERS);){
			
			preparedst.setString(1, ConnectionDb.month.get(LocalDate.now().getMonth()));
			preparedst.setLong(2, LocalDate.now().getYear());
			rs = preparedst.executeQuery();
			while(rs.next()) {
				member = new SendMailModel();
				member.setHouseId(rs.getLong("houseId"));
				member.setMemName(rs.getString("memName"));
				member.setEmailId(rs.getString("emailId"));
				member.setContact(rs.getLong("contact"));
				member.setMonth(rs.getString("month"));
				member.setYear(rs.getLong("year"));
				member.setStatus(rs.getString("status"));
				member.setAmount(rs.getLong("amount"));
				member.setFine(rs.getBoolean("fine"));
				
				list.add(member);
			}
			for(SendMailModel i : list) {
			
				final String username= "bxxxxxr@gmail.com";
				final String password = "xxxxx";
				String subject = "Bill Payment";
				String body = "Hii "+ i.getMemName() +". Your Bill "+ i.getAmount() +" for "+ i.getMonth() +", "+ i.getYear() +" is pending. Pay this bill before date 10 of this month else you have to pay the fine for bill.";
				
				sendFromGmail(username, password, i.getEmailId(), subject, body);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public String selectEmail(long houseId) {
		
		ResultSet rs= null;
		String emailId = "";
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement(mail_user);){
			
			preparedst.setLong(1, houseId);
			rs = preparedst.executeQuery();
			
			while(rs.next()) {
				
				emailId = rs.getString("emailId");
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return emailId;
	}
	
	public List<SendMailModel> getAllDetails() {
		SendMailModel member = null;
		
		List<SendMailModel> list = new ArrayList<>();
		
		ResultSet rs= null;
		try(Connection conn = ConnectionDb.getConnection();
				PreparedStatement preparedst = conn.prepareStatement("select s.houseId, s.memName, s.emailId, s.contact, b.month, b.year, b.status, b.amount, b.fine from society s join billpayment b on s.houseId = b.houseId");){

			rs = preparedst.executeQuery();
			while(rs.next()) {
				member = new SendMailModel();
				member.setHouseId(rs.getLong("houseId"));
				member.setMemName(rs.getString("memName"));
				member.setEmailId(rs.getString("emailId"));
				member.setContact(rs.getLong("contact"));
				member.setMonth(rs.getString("month"));
				member.setYear(rs.getLong("year"));
				member.setStatus(rs.getString("status"));
				member.setAmount(rs.getLong("amount"));
				member.setFine(rs.getBoolean("fine"));
				
				list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
