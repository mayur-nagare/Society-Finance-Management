<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>

	<%
	    String type =  (String)session.getAttribute("uType");
	    long houseId = (long)session.getAttribute("houseId");
	    
	    if(houseId == 0 || type == null || !type.equalsIgnoreCase("worker")){
	    	response.sendRedirect("kill.jsp");
	    }
	%>
	

<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>

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
      <li class="nav-item active">
        <a class="nav-link" href="Worker.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/getList?houseId='<%=session.getAttribute("houseId") %>'">Request Payment</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="WPasswordChange.jsp">Change Password</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
      </li>
    </ul>
  </div>
</nav>
	
	
	<div class="container col-md-5" style="padding-top: 112px;">
		<div class="card">
			<div class="card-body">
				
				<form action="wchng" method="post">
				<caption>
					<h2>
						Change the Password
						<br>
						 <span style="color:red">${msg}</span>
					</h2>
				</caption>
				
	       	
	       		<fieldset class="form-group">
	       				<input type="hidden" name="houseId" class="form-control" placeholder="HouseId" value="<%=session.getAttribute("houseId") %>" />
	       		</fieldset>
	       		
	       		<fieldset class="form-group">
	       		<label>Old Password:</label>
	       				<input type="password" name="password" class="form-control" placeholder="Old Password" value=""<%=request.getAttribute("password") %>" id="myInput1" />
						<input type="checkbox" onclick="myFunction()">&nbsp;Show Password 
	       		</fieldset>
	       		
	       		<fieldset class="form-group">
	       		<label>New Password:</label>
	       				<input type="password" name="pwd1" class="form-control" placeholder="New Password" id="myInput2" />
						<input type="checkbox" onclick="myFunction1()">&nbsp;Show Password 
	       		</fieldset>
				
				<fieldset class="form-group">
	       		<label>Confirm Password:</label>
	       				<input type="password" name="pwd2" class="form-control" placeholder="Confirm Password" id="myInput3" />
						<input type="checkbox" onclick="myFunction2()">&nbsp;Show Password 
	       		</fieldset>
	       		
	       		<input type="submit" class="btn btn-success" value="Change Password" />
			
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
	function myFunction1() {
		var y = document.getElementById("myInput2")
		if (y.type === "password") {
			y.type = "text";
		} else {
			y.type = "password";
		}
	}
	function myFunction2() {
		var z = document.getElementById("myInput3")
		if (z.type === "password") {
			z.type = "text";
		} else {
			z.type = "password";
		}
	}
	</script>

</body>
</html>





