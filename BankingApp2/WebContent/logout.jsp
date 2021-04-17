<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Cookie ck=new Cookie("name","");
ck.setMaxAge(0);
response.addCookie(ck);
%>
<jsp:forward page="Login.jsp"></jsp:forward>
</body>
</html>