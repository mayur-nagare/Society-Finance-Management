package com.mayur.sfc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.LoginDao;
import com.mayur.sfc.model.MemberModel;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long houseId = Long.parseLong(request.getParameter("houseId").trim());
		String password = request.getParameter("password");
		
		MemberModel loginbean = new MemberModel(houseId , password);

		LoginDao dao = new LoginDao();
		
		try {
			
			String userValidate = dao.userAuthenticate(loginbean);
			if(userValidate.equalsIgnoreCase("Admin_Role")) {
				System.out.println("Admin");
				
				HttpSession session = request.getSession();
				session.setAttribute("uType", "admin");
				session.setAttribute("houseId", houseId);
				
				request.getRequestDispatcher("Admin.jsp").forward(request, response);;
				
			}else if(userValidate.equalsIgnoreCase("User_Role")) {
				System.out.println("User");
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("uType", "user");
				session.setAttribute("houseId", houseId);
				
				request.getRequestDispatcher("User.jsp").forward(request, response);
				
			}else if(userValidate.equalsIgnoreCase("Worker_Role")) {
				System.out.println("Worker");
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("uType", "worker");
				session.setAttribute("houseId", houseId);
				request.getRequestDispatcher("Worker.jsp").forward(request, response);
				
			}else {
				System.out.println("Error Messsage = "+userValidate);
				request.setAttribute("errMsg", userValidate);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}catch(Exception e2) {
			
		}
	}

}
