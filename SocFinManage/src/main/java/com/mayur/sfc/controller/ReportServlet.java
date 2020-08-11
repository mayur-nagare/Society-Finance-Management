package com.mayur.sfc.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.PDFGenerateDao;
import com.mayur.sfc.model.SendMailModel;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PDFGenerateDao dao = null;
    
    public void init() {
    	dao = new PDFGenerateDao();
    }

    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }   
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<SendMailModel> list = dao.getData();
		RequestDispatcher dispatcher= request.getRequestDispatcher("pdfPage.jsp");
		request.setAttribute("list", list);
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
