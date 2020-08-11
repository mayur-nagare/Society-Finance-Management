<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</head>
<body style="padding-top: 259px;">

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				
				<form action="mailsend" method="post">
				<caption>
					<h2>
						Reset Password
						<br>
						 <span style="color:red">${msg}</span>
					</h2>
					<h5>Enter Email to reset your password </h5>
				</caption>

	       	
	       		<fieldset class="form-group">
	       		<label>Email Id:</label>
	       				<input type="text" name="emailId" class="form-control" placeholder="Email" id="myInput1" /> 
	       		</fieldset>
	       		
	       		
	       		<input type="submit" class="btn btn-success" value="Send Email" />
			
				</form>
			</div>
		</div>
	</div>

</body>
</html>