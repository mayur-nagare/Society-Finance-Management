package com.mayur.sfc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.SendMailDao;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.SendMailModel;
import com.mayur.sfc.model.paymentModel;


public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SendMailDao dao;
    
    public void init() {
		 dao = new SendMailDao();

	}

    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<SendMailModel> listMember = dao.getAllDetails();		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mailSend.jsp");
		request.setAttribute("listMember", listMember);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
