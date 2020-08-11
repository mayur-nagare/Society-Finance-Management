package com.mayur.sfc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.sfc.dao.LoginDao;
import com.mayur.sfc.dao.PasswordChangeDao;
import com.mayur.sfc.dao.SocietyDao;
import com.mayur.sfc.model.MemberModel;

@WebServlet("/")
public class GetSocietyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PasswordChangeDao passdao;
	private SocietyDao dao;
	
	public void init() {
		 dao = new SocietyDao();

	}
	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			
			switch(action) {
			
			case "/home":
				showNewForm(request, response);
			break;
			
			case "/insert":
				insertMember(request, response);
				break;
				
			case "/edit" :
				showEditMember(request, response);
				break;
				
			case "/update" : 
				updateMember(request, response);
				break;
			
			case "/delete":
				deleteMember(request, response);
				break;
				
			case "/chng":
				adminPassChange(request, response);
				break;
				
			case "/mchng" :
				memberPassChange(request, response);
				break;
				
			case "/wchng" :
				workerPassChange(request, response);
				break;
				
			default:
				listMember(request, response);
				break;
			
			}
		}catch(SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	
	private void listMember(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		List<MemberModel> listMember = dao.getAllMembers();
		request.setAttribute("listMember", listMember);
		RequestDispatcher dispatcher = request.getRequestDispatcher("memberview.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("memberEntry.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
	private void showEditMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException , SQLException{
		
		long houseId = Long.parseLong(request.getParameter("houseId"));
		MemberModel existMember = dao.selectMember(houseId) ;
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditMember.jsp");
		request.setAttribute("member", existMember);
		dispatcher.forward(request, response);
		
	}
	
	
	private void insertMember(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, SQLException {
		long houseId = Long.parseLong(request.getParameter("houseId"));
		String memName = request.getParameter("memName");
		String emailId = request.getParameter("emailId");
		long contact = Long.parseLong(request.getParameter("contact"));
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		MemberModel newMem = new MemberModel(houseId, memName, emailId, contact, password, role);
		dao.insertMember(newMem);
		response.sendRedirect("list");
	}
	
	
	private void updateMember(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		long houseId = Long.parseLong(request.getParameter("houseId").trim());
		String memName = request.getParameter("memName");
		String emailId = request.getParameter("emailId");
		long contact = Long.parseLong(request.getParameter("contact"));
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		MemberModel mem = new MemberModel(houseId, memName, emailId, contact, password, role);
		dao.updateMember(mem);
		response.sendRedirect("list");
	}
	
	private void deleteMember(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		long houseId = Long.parseLong(request.getParameter("houseId"));
		dao.deleteMember(houseId);
		response.sendRedirect("list");
	}
	
	private void adminPassChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ps = request.getParameter("pwd1");
		long houseId = Long.parseLong(request.getParameter("houseId"));
		MemberModel mem = new MemberModel(houseId, ps);
		passdao = new PasswordChangeDao();
		try {
			boolean str = passdao.ChangePassword(mem);
			if(str == true) {
				
				request.setAttribute("msg", "Password is updated");
				request.getRequestDispatcher("changePassword.jsp").forward(request, response);
			}
			else{
				request.setAttribute("msg", "Password updation failed !!!");
				request.getRequestDispatcher("changePassword.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void memberPassChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ps = request.getParameter("pwd1");
		long houseId = Long.parseLong(request.getParameter("houseId"));
		MemberModel mem = new MemberModel(houseId, ps);
		passdao = new PasswordChangeDao();
		try {
			boolean str = passdao.ChangePassword(mem);
			if(str == true) {
				
				request.setAttribute("msg", "Password is updated");
				request.getRequestDispatcher("MChangePassword.jsp").forward(request, response);
			}
			else{
				request.setAttribute("msg", "Password is updated failed !!!");
				request.getRequestDispatcher("MChangePassword.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void workerPassChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ps = request.getParameter("pwd1");
		long houseId = Long.parseLong(request.getParameter("houseId"));
		MemberModel mem = new MemberModel(houseId, ps);
		passdao = new PasswordChangeDao();
		try {
			boolean str = passdao.ChangePassword(mem);
			if(str == true) {
				
				request.setAttribute("msg", "Password is updated");
				request.getRequestDispatcher("WPasswordChange.jsp").forward(request, response);
			}
			else{
				request.setAttribute("msg", "Password is updated failed !!!");
				request.getRequestDispatcher("WPasswordChange.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
