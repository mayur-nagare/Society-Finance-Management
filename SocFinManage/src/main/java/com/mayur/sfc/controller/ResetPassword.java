package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.ResetPasswordDao;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.ResetPasswordModel;

@WebServlet("/Reset")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ResetPasswordDao dao;
 
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("emailId");
		String newPass = request.getParameter("pwd1");
		MemberModel mem = new MemberModel(mail, newPass);
		
		dao = new ResetPasswordDao();
		try {
			
			boolean str = dao.ChangePassword(mem);
			if(str == true) {
				
				request.setAttribute("msg", "Password reset successfull!!!");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			else{
				request.setAttribute("msg", "Password reset failed !!!");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}

		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
