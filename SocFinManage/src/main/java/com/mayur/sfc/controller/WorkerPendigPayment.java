package com.mayur.sfc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.RequestPaymentDao;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.SocietyWorkerModel;


public class WorkerPendigPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequestPaymentDao dao;

    public WorkerPendigPayment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new RequestPaymentDao();
		List<SocietyWorkerModel> listMember = dao.showWorkerPendingList();
		if(!listMember.isEmpty()) {
		request.setAttribute("listMember", listMember);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WorkerPendingPayment.jsp");
		dispatcher.forward(request, response);
		}else {
			request.setAttribute("msg", "No request yet");
			request.getRequestDispatcher("WorkerPendingPayment.jsp").forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
