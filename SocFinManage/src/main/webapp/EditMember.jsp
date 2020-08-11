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
<title>Edit Member</title>

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
      <li class="nav-item dropdown active">
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

<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				
				<c:if test="${member != null}">
					<form action="update" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${member != null}">
						Edit Member
						</c:if>
					</h2>
				</caption>
				
				<c:if test="${member != null }">
				<fieldset class="form-group">
	       		<label>HouseId:</label>
	        		<input type="hidden" name="houseId" value="${member.houseId}" /><br>${member.houseId}
	        	</fieldset>
	        	</c:if>
	        	
	       		<fieldset class="form-group">
	       		<label>Member Name:</label>
	       				<input type="text" name="memName" class="form-control" required="required" value="${member.memName}" />
	       		</fieldset>
	       		
				<fieldset class="form-group">
	       		<label>Email:</label>
	       				<input type="text" name="emailId" class="form-control"  value="${member.emailId}" />
	       		</fieldset>
	       		
	       		<fieldset class="form-group">
	       		<label>Contact:</label>
	       				<input type="text" name="contact" class="form-control" value="${member.contact}" />
	       		</fieldset>
	       		
	       		<fieldset class="form-group">
	       		<label>Password:</label>
	       				<input type="password" name="password" class="form-control" value="${member.password}" id="myInput1" />
	       				<input type="checkbox" onclick="myFunction()">&nbsp;Show Password
	       		</fieldset>
	       		
	       		<fieldset class="form-group">
	       		<label>Role:</label>
	       				<input type="text" name="role" class="form-control" value="${member.role}" />
	       		</fieldset>
	       		
	       		<input type="submit" class="btn btn-success" value="Save" />
			
				</form>
			</div>
		</div>
	</div>
	
	<script>
		function myFunction() {
			var x= document.getElementById("myInput1")
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}	
		}
	</script>
	
</body>
</html>