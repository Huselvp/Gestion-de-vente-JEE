<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Page</title>
</head>
<body>
<h3>Bienvenu, entrez vos donnees</h3>
<s:form action="login">
    <s:textfield name="login" label="username"></s:textfield>
    <s:textfield name="password" label="Password" type="password"></s:textfield>
    <s:submit value="Login"></s:submit>
</s:form>
</body>
</html>