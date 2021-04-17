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
String name1="";
HttpSession ss=request.getSession();
ss.setAttribute("name", name1);
 %>            

<%


Cookie ck[]=request.getCookies();
String name="";
if(ck!=null)
{
	for(int i=0;i<ck.length;i++)
	{
		name=ck[i].getValue();
		System.out.println("Name is"+name);
	}
	if(name.length()<10)
	{
		%>
		<jsp:forward page="Loginpage.jsp"></jsp:forward>
		<%
	}else{
		%>
		<%@ include file="Loginpage.jsp" %>
		<%
		}
}else{
	%>
	<%@ include file="Loginpage.jsp" %>
	<%
}
%>

</body>
</html>