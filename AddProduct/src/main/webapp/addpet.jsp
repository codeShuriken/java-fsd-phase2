<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Pet</title>
</head>
<body>


<form action="pets" method="post">
  <label for="name">Name:</label><br>
  <input type="text" required  id="name" name="name"><br>
  
  <label for="color">Color:</label><br>
  <input type="text" required id="color" name="color"><br>
  
  <label for="price">Price:</label><br>
  <input type="number" required step=".01" id="price" name="price"><br><br>
  
  <input type="submit" value="Submit">
  
  <br><br>
  <a href = "index.jsp"> Go Back </a>
</form>



</body>
</html>