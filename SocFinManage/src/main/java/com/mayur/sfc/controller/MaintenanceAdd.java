package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.PayBillDao;
import com.mayur.sfc.model.paymentModel;

@WebServlet("/maint")
public class MaintenanceAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PayBillDao paydao;
    
    public void init() {
    	paydao = new PayBillDao();
    }

    public MaintenanceAdd() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int maint = 0;
		try{
			String month = request.getParameter("month");
			long year = Long.parseLong(request.getParameter("year"));
			long wcharge = Long.parseLong(request.getParameter("wcharge"));
			long mcharge = Long.parseLong(request.getParameter("mcharge"));
			long echarge = Long.parseLong(request.getParameter("echarge"));
			
			
			long totalAmount = wcharge + mcharge + echarge;
			
			paymentModel maintenance = new paymentModel(month, year, wcharge, mcharge, echarge, totalAmount);
			maint = paydao.mainBill(maintenance);
			
			if(maint > 0){
				request.setAttribute("msg","Monthly Maintenace Added....!!!");
				request.getRequestDispatcher("generateBill.jsp").forward(request, response);
			}else {
				request.setAttribute("msg","Failed to Add....!!!");
				request.getRequestDispatcher("generateBill.jsp");
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
