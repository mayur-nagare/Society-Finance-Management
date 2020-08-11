<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>
		Contact Us
	</title>
 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<style type="text/css">
	<%@ include file="/css/index.css"%>
	<%@ include file="/css/contact.css"%>
</style>

</head>
<body>

	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <header class="masthead mb-auto">
    <div class="inner">
      <h3 class="masthead-brand">Society Finance Management</h3>
      <nav class="nav nav-masthead justify-content-center">
        <a class="nav-link" href="index.jsp">Home</a>
        <a class="nav-link" href="Login.jsp">Log In</a>
        <a class="nav-link active" href="contactUs.jsp">Contact</a>
      </nav>
    </div>
  </header>

  <main role="main" class="inner cover">
  			<p class="lead">${msg}</p>
		    <h2>Contact Us</h2>
	
		      <form action="feedback" method="post">
		        <label>Name</label>
		        <input type="text" name="Name" placeholder="Your Name..">
		        <label>Email</label>
		        <input type="text" name="emailId" placeholder="Your Email..">
		        <label>Subject</label>
		        <textarea name="subject" placeholder="Write something.." style="height:170px"></textarea>
		        <input type="submit" value="Submit">
		      </form>
  </main>

  <footer class="mastfoot mt-auto">
    <div class="inner">
      
    </div>
  </footer>
</div>

	
</body>
</html>
