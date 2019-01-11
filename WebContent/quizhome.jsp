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
<h2>Questions </h2>
<s:form action="evaluate">
<s:iterator value="questions" status="counter1">

 <s:property value="%{#counter1.count}"/>. <s:property value="question"/>
    <br>
    
    <s:iterator value="choices" status="counter2"> 
  	<s:radio theme="simple" name="option%{#counter1.index}" list="#{idChoice:choice}"/><br>
	</s:iterator> 
<hr>
 </s:iterator>
 <s:submit/>
 </s:form>


</body>
</html>


