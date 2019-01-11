<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<center>
<h1>Online Intelligent Quiz Portal</h1>
</center>
<hr>
</head>
<body>
<div style="border-width: 2px; border-style: dashed; border-color: red;" align="center">
<h2>Enter User Details</h2>
<s:form action="quizAction" >
	<s:textfield name="firstName" label="First Name " value="%{#session.user.firstName}"/>
	<s:textfield name="lastName" label="Last Name " value="%{#session.user.lastName}"/>
	<s:textfield name="emailId" label="Email ID " value= "%{#session.user.emailId}"/>
	<s:textfield name="age" label="Age " value="%{#session.user.age}"/>
	<s:radio name="gender" label="Gender " list="{'Male','Female'}" value="%{#session.user.gender}" />
    
    <s:select name="difficultyLevel" list="{'0','1','2','3','4','5','6','7','8','9'}" headerKey=""
        headerValue="Select Difficulty Level" label="Difficulty Level " />
        
    <s:select name="noOfQuestions" list="{'5','25','50','100'}" headerKey=""
        headerValue="Select Number of Questions" label="Number of Questions " />    
    
<s:submit name="submit" label="Start Quiz" align="center"/>
</s:form>
</div>
 </body>
</html>