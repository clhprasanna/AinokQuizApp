<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Intelligent Quiz Portal</title>
<center>
<h1>Online Intelligent Quiz Portal</h1>
<hr>
</center>
</head>
<body>
<h2>Quiz Completed Successfully.</h2>
<br>
<b>Your Total Score is : <s:property value="#session.percentage" /> %</b>

<br>

<a href="/AikonQuizApp">Home</a>

</body>
</html>