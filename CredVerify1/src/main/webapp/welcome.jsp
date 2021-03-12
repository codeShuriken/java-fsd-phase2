<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (session.getAttribute("user") == null){
			response.sendRedirect("index.jsp");
		}
	%>
	
	You're successfully logged in ${user} !!!
	<br>
	<br>
	<form action="Logout">
		<input type="submit" value="Logout">
	</form>
</body>
</html>