<html>
<head>
	<script src="validate.js"></script>
</head>
<body>
	<h1 style="text-align: center;">Register Here</h1>

	<form action="userRegister" method="post">
	
		<label for="email">Email</label><br> 
		<input type="text" required id="email" name="email" onkeyup="validateEmail()"><br><br> 
		
		<label for="password">Password</label>
		<br> 
		<input type="password" required id="password" name="password"><br> 
		<br>
		
		<label for="confirm_password">Confirm Password</label>
		<br> 
		<input type="password" required id="confirm_password" name="confirm_password" onkeyup="validatePassword()"><br> 
		<br>
		<input type="submit" value="Register">
	</form>
	
	<a href="index.jsp"> Already have an Account? (Login Here) </a>
</body>
</html>