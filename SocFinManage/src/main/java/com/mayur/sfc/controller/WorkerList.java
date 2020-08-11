package com.mayur.sfc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.SocietyDao;
import com.mayur.sfc.model.MemberModel;

@WebServlet("/getList")
public class WorkerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SocietyDao dao;

    public WorkerList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long houseId = (Long)session.getAttribute("houseId");
		dao = new SocietyDao();
		MemberModel existMember = dao.selectMember(houseId) ;
		RequestDispatcher dispatcher = request.getRequestDispatcher("Requestpayment.jsp");
		request.setAttribute("member", existMember);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
