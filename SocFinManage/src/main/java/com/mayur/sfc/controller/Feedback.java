package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.Connection;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Feedback() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("Name");
		String emailId = request.getParameter("emailId");
		String body1 = request.getParameter("subject");
		
		final String username= "bxxxxxr@gmail.com";
		final String password = "xxxxx";
		String subject = "Feed Back";
		String body = "Name is : "+name+"\n Mail From : "+emailId+"\n Message :\n"+body1;
		
		
		sendFromGmail(username, password, subject, body);
		
		
		request.setAttribute("msg", "Mail sent Successfully.");
		request.getRequestDispatcher("contactUs.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
