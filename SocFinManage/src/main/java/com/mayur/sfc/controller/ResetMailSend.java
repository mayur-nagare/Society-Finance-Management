package com.mayur.sfc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.ResetPasswordDao;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.ResetPasswordModel;


@WebServlet("/mailsend")
public class ResetMailSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ResetPasswordDao dao;
    
    public void init() {
    	dao = new ResetPasswordDao();
    }
    public ResetMailSend() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String mail = request.getParameter("emailId");
		
		MemberModel mem;
		ResetPasswordModel resetPassword;
		try {
			ArrayList<Object> list = dao.checkMail(mail);
			mem = (MemberModel)list.get(0);
			resetPassword = (ResetPasswordModel)list.get(1);
			
			if(mem.getEmailId().equals(mail)) {
				
				long token = resetPassword.getToken();
				
				final String username= "bxxxxxr@gmail.com";
				final String password = "xxxxx";
				String subject = "Reset Password Link";
				String content = "Hii "+ mem.getMemName() +", here is the link to reset your password.<br>Please Click <a href='http://localhost:9090/SocFinManage/resetPassword/token/"+token+"/"+resetPassword.getEmailId()+"'> here to reset your password.</a>";
				sendFromGmail(username, password, mail, subject, content);
				request.setAttribute("msg", "Mail Send Successfully");
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Email doesn't found...Enter valid Email...");
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static void sendFromGmail(final String username, final String password, String emailId, String subject, String content) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		
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
			message.setContent(content, "text/html");
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
