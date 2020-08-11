<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
<title>Generate Bill</title>

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

	<div class="container" style="padding-top: 112px;">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="container">
					<form action="maint" method="post">
						<h2 class="text-center">Generate Bill
						<br>
						<span style="color:red">${msg}</span>
						</h2>
						<hr>
						<table class="table table-bordered">
							<thead>
								<tr bgcolor="00FF7F">
									<th>Sr. No</th>
									<th>month</th>
									<th>year</th>
									<th>charges</th>
									<th>Amount</th>
								</tr>
							</thead>
							
							<tbody>
									<tr>
										<td>1</td>
										<td rowspan = "3"><fieldset class="form-group">
															<select class="form-control" name="month">
																<option value="Jan" default>Jan</option>
																<option value="Feb">Feb</option>
																<option value="Mar">Mar</option>
																<option value="Apr">Apr</option>
																<option value="May">May</option>
																<option value="June">June</option>
																<option value="July">July</option>
																<option value="Aug">Aug</option>
																<option value="Sep">Sep</option>
																<option value="Oct">Oct</option>
																<option value="Nov">Nov</option>
																<option value="Dec">Dec</option>
															</select>
														</fieldset>
										<td rowspan = "3"> <fieldset class="form-group">
																<select class="form-control" name="year" >
																	<option value="2021">2021</option>
																	<option value="2020" default>2020</option>
																	<option value="2019">2019</option>
																	<option value="2018">2018</option>
																</select>
															</fieldset>
										<td> Water Charge</td>
										<td> <input type = "text" name= "wcharge"></td>
									</tr>
									<tr>
										<td>2</td>
										<td> Maintenance Charge</td>
										<td> <input type = "text" name= "mcharge"></td>
									</tr>
									<tr>
										<td>3</td>
										<td>Electricity Charge</td>
										<td> <input type = "text" name= "echarge"></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><input type="submit" class="btn btn-success" value="Submit" style="color:#fff;background-color:#6c757d;border-color:#6c757d"/></td>
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