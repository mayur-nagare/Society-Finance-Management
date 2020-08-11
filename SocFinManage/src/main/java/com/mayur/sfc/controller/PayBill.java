package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.PayBillDao;
import com.mayur.sfc.model.SendMailModel;
import com.mayur.sfc.model.paymentModel;

@WebServlet("/paybill")
public class PayBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PayBillDao paydao;

    public void init() {
    	paydao = new PayBillDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billpaid= "";
		try {
			long houseId = Long.parseLong(request.getParameter("houseId"));
			String month = request.getParameter("month");
			long year = Long.parseLong(request.getParameter("year"));
			long amount = Long.parseLong(request.getParameter("amount"));
			
			SendMailModel payment = new SendMailModel(houseId, month, year, amount);
			billpaid = paydao.payBill(payment);
			
			if(billpaid.equalsIgnoreCase("paid partially")) {
				request.setAttribute("msg","Payment Partially Done...!!!");
				request.getRequestDispatcher("billpayment.jsp").forward(request, response);
			}else if(billpaid.equalsIgnoreCase("paid fully")) {
				request.setAttribute("msg","Payment Done...!!!");
				request.getRequestDispatcher("billpayment.jsp").forward(request, response);
			}else if(billpaid.equalsIgnoreCase("paid already")) {
				request.setAttribute("msg","Payment Already Done...!!!");
				request.getRequestDispatcher("billpayment.jsp").forward(request, response);
			}
		} catch (SQLException e) {
				
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
