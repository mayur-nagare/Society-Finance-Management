package com.mayur.sfc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String msg ;
		
		if(String.valueOf(session.getAttribute("uType")).equalsIgnoreCase("admin") || String.valueOf(session.getAttribute("uType")).equalsIgnoreCase("user")){
			msg = "You have Logged Out";
		}else{
			msg = "Unauthorized Access";
		}
		session.invalidate();
		request.setAttribute("errMsg", msg);
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		System.out.println("Logged Out");
		
		
//		if(session != null) {
//			session.invalidate();
//			request.setAttribute("errMsg", "You have Logged Out");
//			request.getRequestDispatcher("Login.jsp").forward(request, response);
//			System.out.println("Logged Out");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
