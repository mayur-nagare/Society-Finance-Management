<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<%
	    String type =  (String)session.getAttribute("uType");
	    long houseId = (long)session.getAttribute("houseId");
	    
	    if(houseId == 0 || type == null || !type.equalsIgnoreCase("user")){
	    	response.sendRedirect("kill.jsp");
	    }
	%>
	

<head>
<meta charset="ISO-8859-1">
<title>Pay Bill</title>

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
        <a class="nav-link" href="User.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="MpayBill.jsp">Bill Payment</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="MChangePassword.jsp">Change Password</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
      </li>
    </ul>
  </div>
</nav>


<div class="container col-md-5" style="padding-top: 130px;">
		<div class="card">
			<div class="card-body">	
			<form action="MbPayer" method="post">
				<caption>
					<h2>
						Pay Bill
					</h2>
				</caption>
					        	
	       		<fieldset class="form-group">
	       		<label>houseId:</label>
	       				<input type="hidden" name="houseId" class="form-control" required="required" value="<%=session.getAttribute("houseId")%>" /><br>&nbsp<%=session.getAttribute("houseId")%>
	       		</fieldset>
	       		
				<fieldset class="form-group">
				<label>Month:</label>
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
	       		
	       		<fieldset class="form-group">
	       		<label>Year:</label>
					<select class="form-control" name="year" >
						<option value="2021">2021</option>
						<option value="2020" default>2020</option>
						<option value="2019">2019</option>
						<option value="2018">2018</option>
					</select>
				</fieldset>
	       		
	       		
	       		<input type="submit" class="btn btn-success" value="Get Bill" />
			
				</form>
			</div>
		</div>
	</div>

</body>
</html>