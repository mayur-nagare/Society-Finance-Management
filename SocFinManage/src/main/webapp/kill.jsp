<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<%
String msg;
	if(String.valueOf(session.getAttribute("uType")).equalsIgnoreCase("admin") || String.valueOf(session.getAttribute("uType")).equalsIgnoreCase("member")){
		msg = "Successfully Logged out !!!";
		
	}
	else{
		msg = "Unauthorised Access !!!";
	}
   session.invalidate();
   RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
   request.setAttribute("errMsg", msg);
   rd.forward(request,response);
%>
</body>
</html>