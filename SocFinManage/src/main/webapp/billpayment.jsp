<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<meta charset="ISO-8859-1">
<title>Bill Payment</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

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

<div class="container" style="padding-top: 92px;">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="container">
						<c:if test="${payer != null}">
								<form action="paybill" method="post">
							</c:if>
							<caption>
								<h2 class="text-center">
									<c:if test="${payer != null}">
									Bill Payment
									</c:if>
									<span style="color:red">${msg}</span>
								</h2>
							</caption>
						<hr>
						<c:if test="${payer != null }">
				        		<input type="hidden" name="houseId" value="${payer.houseId}" />
				        	</c:if>
						<table class="table table-bordered">
							<thead>
								<tr bgcolor="00FF7F">
									<th>houseId</th>
									<th>month</th>
									<th>year</th>
									<th>charges</th>
									<th>Amount</th>
									<th>Date</th>
								</tr>
							</thead>
							
							<tbody>
									<tr>
										<td rowspan= "3"><input type="hidden" class="form-control" name="houseId" placeholder="Flat Number" aria-label="Flat Number" value="${payer.houseId}" aria-describedby="basic-addon1">${payer.houseId}</td>
										
										<td rowspan = "3"><input type="hidden" class="form-control" name="month" placeholder="Month" required="required" value="${payer.month}" aria-label="Flat Number" aria-describedby="basic-addon1">${payer.month}</td>
										
										<td rowspan = "3"><input type="hidden" class="form-control" name="year" placeholder="Year" required="required" value="${payer.year}" aria-label="Flat Number" aria-describedby="basic-addon1">${payer.year}</td>
										
										<td> Water Charge</td>
										
										<td><input type="hidden" class="form-control" name="wcharge" placeholder="WCharge" required="required" value="${payer.wcharge}" aria-label="Flat Number" aria-describedby="basic-addon1">${payer.wcharge}</td>
										
										<td rowspan= "3"><input type="hidden" class="form-control" name="date" placeholder="Date" required="required" value="<%=LocalDate.now().toString()%>"  aria-label="Flat Number" aria-describedby="basic-addon1"><%=LocalDate.now().toString()%></td>
									</tr>
									<tr>
										<td> Maintenance Charge</td>
										<td><input type="hidden" class="form-control" name="mcharge" placeholder="MCharge" required="required" value="${payer.mcharge}" aria-label="Flat Number" aria-describedby="basic-addon1">${payer.mcharge}</td>
									</tr>
									<tr>
										<td>Electricity Charge</td>
										<td><input type="hidden" class="form-control" name="echarge" placeholder="ECharge" required="required" value="${payer.echarge}" aria-label="Flat Number" aria-describedby="basic-addon1">${payer.echarge}</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td>Total Amount</td>
										<td><input type="text" class="form-control" name="amount" placeholder="Amount" required="required" value="${payer.amount}" aria-label="Flat Number" aria-describedby="basic-addon1"></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><input type="submit" class="btn btn-success" value="Submit" style="color:#fff;background-color:#6c757d;border-color:#6c757d"/></td>
										<td></td>
									</tr>
						 	</tbody>
						</table>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
		
</body>
</html>


