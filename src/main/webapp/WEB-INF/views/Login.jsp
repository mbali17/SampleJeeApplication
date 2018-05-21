<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Welcome to the TODO app. Please login to continue </h1>
<form method="post" action="login">
    <label for="userName">Enter UserName</label>
    <input type="text" placeholder="Enter User Name" id="userName" name="userName">
    <br/>
    <label for="password">Enter Password</label>
    <input type="password" placeholder="Enter Password" id="password" name="password">
    <input type="submit" value="login">
</form>
</body>
</html>