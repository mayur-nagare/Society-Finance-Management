package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mayur.sfc.dao.PayBillDao;
import com.mayur.sfc.model.MemberModel;
import com.mayur.sfc.model.paymentModel;

@WebServlet("/Bpayer")
public class BillPayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PayBillDao dao;
    
    public void init() {
    	dao = new PayBillDao();
    }

    public BillPayer() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		long houseId = Long.parseLong(request.getParameter("houseId"));
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		
		paymentModel payer;
		try {
			payer = dao.selectPayer(houseId, month, year);
			request.setAttribute("payer", payer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("billpayment.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
