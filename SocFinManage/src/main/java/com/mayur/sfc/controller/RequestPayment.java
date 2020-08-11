package com.mayur.sfc.controller;

import java.io.IOException;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.RequestPaymentDao;


public class RequestPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequestPaymentDao dao;

    
    public RequestPayment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long houseId = Long.parseLong(request.getParameter("houseId"));
		String memName = request.getParameter("memName");
		String emailId = request.getParameter("emailId");
		String month = request.getParameter("month");
		long year = Long.parseLong(request.getParameter("year"));
		long amount = Long.parseLong(request.getParameter("amount"));
		String ac_no = request.getParameter("ac_no");
		
		dao = new RequestPaymentDao();
		try {
			boolean str = dao.sendPaymentRequest(houseId, memName, emailId, month, year, amount);
			if(str == true) {
				final String username= "bxxxxxr@gmail.com";
				final String password = "xxxxx";
				String subject = "Worker Pending Payment";
				String body = "Hii, I am ("+ houseId +")"+ memName +".\nMy Work Payment "+ amount +" for "+ month +", "+ year +" is Pending.\nPlease approve my payment.\nThank you.\nFrom, "+emailId;
				
				sendFromGmail(username, password, subject, body);
				
				request.setAttribute("msg", "Your request Sent. Wait for Approval.");
				request.getRequestDispatcher("Requestpayment.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Please Enter Valid Details.");
				request.getRequestDispatcher("Requestpayment.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
