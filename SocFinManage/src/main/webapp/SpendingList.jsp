
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<%
	    String type =  (String)session.getAttribute("uType");
	    long houseId = (long)session.getAttribute("houseId");
	    
	    if(houseId == 0 || type == null || !type.equalsIgnoreCase("admin")){
	    	response.sendRedirect("kill.jsp");
	    }
	%>
	
<head>
<meta charset="ISO-8859-1">
<title>pdf</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	
	<script src="https://raw.githack.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js"></script>
	<script>
		function fun(){
			console.log("Generating pdf....")
			var element = document.getElementById('id');
			html2pdf(element);
		}
	</script>

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
      <li class="nav-item dropdown">
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
      <li class="nav-item dropdown active">
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

<div class="container">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="container">
						<div class="btn-group" role="group" aria-label="Basic example">
			  				<button type="button" class="btn btn-secondary" onclick="fun()">Generate PDF</button>
			  			</div>
			  		</div>
			  	</div>
			  	<div class="row" id="id">
					<div class="container">
						<h1 class="text-center">Report</h1>
						<h3 class="text-center">The Yearly Spending details are listed below.</h3>
						<hr>
						<h4 class="text-center">List of Detail</h4>
						<hr>
						<table class="table table-bordered">
							<thead>
								<tr bgcolor="00FF7F">
									<th><b>Sr. No.</b></th>
									<th><b>A/C Name</b></th>
									<th><b>Credited Amount</b></th>
									<th><b>Credited From</b></th>
									<th><b>Debited Amount</b></th>
									<th><b>Debited To</b></th>
									<th><b>Month</b></th>
									<th><b>Year</b></th>
									<th><b>Balance</b></th>
								</tr>
							</thead>
							
							<tbody>
								
								<c:forEach var="mem" items="${list}" varStatus="counter">
									<tr>
										<td>${counter.index+1}</td>
										<td><c:out value="${mem.ac_name}" /></td>
										<td><c:out value="${mem.credit_amt}" /></td>
										<td><c:out value="${mem.credit_from_name}" /></td>
										<td><c:out value="${mem.debit_amt}" /></td>
										<td><c:out value="${mem.debit_to_name}" /></td>
										<td><c:out value="${mem.ct_dt_month}" /></td>
										<td><c:out value="${mem.ct_dt_year}" /></td>
										<td><c:out value="${mem.balance}" /></td>
									</tr>
						 		</c:forEach>
						 	</tbody>
						</table>
					</div>
				</div>
			</div>
  		</div>
  	</div>

</body>
</html>