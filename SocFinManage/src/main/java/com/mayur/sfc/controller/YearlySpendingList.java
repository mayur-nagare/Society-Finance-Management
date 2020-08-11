package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.YearlySpendingDao;
import com.mayur.sfc.model.BankDetailModel;

public class YearlySpendingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    YearlySpendingDao dao;

    public YearlySpendingList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long year = Long.parseLong(request.getParameter("year"));
		dao = new YearlySpendingDao();
		try {
			List<BankDetailModel> list = dao.getList(year);
			
			if(!list.isEmpty()) {
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("SpendingList.jsp").forward(request, response);
		        
			}else {
				request.setAttribute("msg", "No Spending Found");
				request.getRequestDispatcher("SpendingList.jsp").forward(request, response);
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
