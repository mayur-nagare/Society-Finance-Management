package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.ResetPasswordDao;
import com.mayur.sfc.model.ResetPasswordModel;

@WebServlet("/resetPassword/token/*")
public class ResetMailConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResetPasswordDao dao;

    public ResetMailConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] getString = request.getPathInfo().substring(1).split("/");
		String tokenTest = getString[0];
		long token = Long.parseLong(tokenTest);
		String mail = getString[1];
		dao = new ResetPasswordDao();
		try {
			ResetPasswordModel res = dao.getExpTime(token, mail);
			
			long exptime = Long.valueOf(res.gettExp_time());
		    long nowTime = Calendar.getInstance().getTimeInMillis();

			if(exptime-nowTime >0) {
				HttpSession session = request.getSession();
				session.setAttribute("mail",mail);
				response.sendRedirect("/SocFinManage/passwordReset.jsp");
			}else {
				request.setAttribute("msg", "Token Expired !!!");
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
