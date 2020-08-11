package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.RequestPaymentDao;


public class RequestApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequestPaymentDao dao;

    public RequestApproval() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long houseId = Long.parseLong(request.getParameter("houseId"));
		dao = new RequestPaymentDao();
		
		try {
			if(dao.WorkerRequestApproval(houseId)) {
				request.setAttribute("msg", "Approval Done");
				request.getRequestDispatcher("WorkerPendingPayment.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Approval Failed");
				request.getRequestDispatcher("WorkerPendingPayment.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
