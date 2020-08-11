<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<%
	    String type =  (String)session.getAttribute("uType");
	    long houseId = (long)session.getAttribute("houseId");
	    
	    if(houseId == 0 || type == null || !type.equalsIgnoreCase("admin")){
	    	response.sendRedirect("kill.jsp");
	    }
	%>

<head>
<title>Send Mail</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #e3f2fd">
  <a class="navbar-brand" href="#">Society Finance Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="Admin.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Members
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="memberEntry.jsp">Add Members</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/list">Members Details</a>
          <a class="dropdown-item" href="changePassword.jsp">Change Password</a>
        </div>
      </li>
      <li class="nav-item dropdown active">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Bill
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="generateBill.jsp">Generate Bill</a>
          <a class="dropdown-item" href="payBill.jsp">Bill Payment</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/pendingList">Worker Payment</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/send">Send Mail</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Report
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		  <a class="dropdown-item" href="<%=request.getContextPath()%>/report">Delayed Payment</a>
		  <a class="dropdown-item" href="YearlySpending.jsp">Year Spending</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
      </li>
    </ul>
  </div>
</nav>


	<div class="row">
		<div class="container">
			
			<h2 class="text-center">List of Members</h2>
			<span style="color:blue">${msg}</span>
			<hr>
			<table class="table table-bordered">
				<thead>
					<tr bgcolor="00FF7F">
						<th><b>Sr. No.</b></th>
						<th><b>ID</b></th>
						<th><b>Member Name</b></th>
						<th><b>Email ID</b></th>
						<th><b>Month</b></th>
						<th><b>Year</b></th>
						<th><b>Amount</b></th>
						<th><b>Payment Status</b></th>
						<th><b>Action</b></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="mem" items="${listMember}" varStatus="counter">
						<tr>
							<td>${counter.index+1}</td>
							<td><c:out value="${mem.houseId}" /></td>
							<td><c:out value="${mem.memName}" /></td>
							<td><c:out value="${mem.emailId}" /></td>
							<td><c:out value="${mem.month}" /></td>
							<td><c:out value="${mem.year}" /></td>
							<td><c:out value="${mem.amount}" /></td>
							<td><c:out value="${mem.status}" /></td>
							<td><a href="<%=request.getContextPath()%>/requestApprove?houseId=<c:out value='${mem.houseId}'/>">Pay</a></td>
						</tr>
			 		</c:forEach>
			 	</tbody>
			</table>
		</div>
	</div>

</body>
</html>