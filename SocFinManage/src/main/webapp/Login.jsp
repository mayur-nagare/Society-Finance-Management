<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
		
		
<style type="text/css">
			<%@ include file="/css/login.css" %> 
	<%@ include file="/css/index.css"%>
</style>

</head>
<body>
 
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="masthead mb-auto">
    <div class="inner">
      <h3 class="masthead-brand">Society Finance Management</h3>
      <nav class="nav nav-masthead justify-content-center">
        <a class="nav-link" href="index.jsp">Home</a>
        <a class="nav-link active" href="Login.jsp">Log In</a>
        <a class="nav-link" href="contactUs.jsp">Contact</a>
      </nav>
    </div>
  </header>

<!-- <main role="main" class="inner cover">-->
		<form class="form-signin" name="form" action="<%=request.getContextPath()%>/LoginServlet" method="post">
			  
			  <span style="color:blue">${msg}</span>
			  <br>
			  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			  <label for="username" class="sr-only">HouseID</label>
			  <input type="text" name="houseId" class="form-control" placeholder="HouseID" required autofocus>
			  
			 <label for="password" class="sr-only">Password</label>
			  <input type="password" name="password" class="form-control" placeholder="Password" required>
			  
			  <span style="color:red"><%=(request.getAttribute("errMsg") == null)? "" : request.getAttribute("errMsg") %></span>
			  			  
			  <button class="btn btn-lg btn-primary btn-block" type="submit" >Sign in</button>
			  <a href="forgotPassword.jsp">Forgot Password</a>  
			  
		</form>
	<!-- </main>-->

  <footer class="mastfoot mt-auto">
    <div class="inner">
      
    </div>
  </footer>
</div> 
	</body>
</html>