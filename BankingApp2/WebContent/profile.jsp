<%@ page  import="java.sql.*,com.db.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="cyan">

<body>
<%
String name=(String) session.getAttribute("name");
//double balance=0;
try 
{
Connection  cn=DBAccess.getConn();
PreparedStatement ps=cn.prepareStatement("select * from profile where username=(?)");
System.out.println(name);
ps.setString(1, name);
ResultSet res=ps.executeQuery();

while(res.next())
{
	String Id=res.getString(1);
	
	String fullname=res.getString(2);
	String username=res.getString(3);
	String pass=res.getString(4);
	String mobile=res.getString(5);
	String gender=res.getString(6);
	String email=res.getString(7);
	String aadhar=res.getString(8);
	String account=res.getString(9);
	

	%>
	
	<table>
	<body bgcolor="cyan">
	<tr><td>ID</td><td><%=Id%></td></tr>
	
	<tr><td>fullname</td><td><%=fullname%></td></tr>
	<tr><td>username</td><td><%=username%></td></tr>
	<tr><td>password</td><td><%=pass%></td></tr>
	<tr><td>mobile</td><td><%=mobile%></td></tr>
	<tr><td>gender</td><td><%=gender%></td></tr>
	<tr><td>email</td><td><%=email%></td></tr>
	<tr><td>Aadhar</td><td><%=aadhar%></td></tr>
	<tr><td>Account</td><td><%=account%></td></tr>
	
	

	</body>
	</table>
	<hr>
<%
}
}catch (Exception e) {
	e.printStackTrace();
}
%>
</body>
</html>

</body>
</html>