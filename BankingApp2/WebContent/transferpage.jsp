<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="cyan">
<% Cookie ck[]=request.getCookies();
String name="";
if(ck!=null)
{
	for(int i=0;i<ck.length;i++)
	{
		name=ck[i].getValue();
		System.out.println("account no is"+name);
	}
	if(name.length()<10)
	{
		%>
		<jsp:forward page="Home.jsp"></jsp:forward>
		<%
	}else{
		%>
		<%@ include file="transfer.jsp" %>
		<%
		}
}else{
	%>
	<%@ include file="transfer.jsp" %>
	<%
}
%>


</body>
</html>