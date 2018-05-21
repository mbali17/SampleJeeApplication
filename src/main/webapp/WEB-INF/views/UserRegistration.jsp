<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ali.constants.ServletUrlConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<style>
    .error{
        color: red;
    }
</style>
</head>
<body>
<h1>Register Here <h1>
<p class="error">${errorMessage}</p>
<%-- TODO:Add front end validations --%>
<form action="do/register" method="post">
    <label for="userName">Enter User Name:</label>
    <input type="text" name="userName" id="userName"/>
    <br/>
    <label for="email">Enter Email:</label>
    <input type="email" name="email" id="email"/>
    <br/>
    <label for="password">Enter Password:</label>
    <input type="password" name="password" id="password"/>
    <br/>
    <label for="repeatPassword">Repeat Password:</label>
    <input type="password" name="repeatPassword" id="repeatPassword"/>
    <br/>
    <button type="submit" value="Submit">Submit</button>
</form>
</body>
</html>