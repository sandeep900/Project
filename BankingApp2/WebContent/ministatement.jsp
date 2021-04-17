
    <%@ page import="java.sql.*,com.db.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String name=(String) session.getAttribute("name");
double balance=0;
try 
{
Connection  cn=DBAccess.getConn();
PreparedStatement ps=cn.prepareStatement("select * from ministatement1 where username=(?)");
System.out.println(name);
ps.setString(1, name);
ResultSet res=ps.executeQuery();
int i=0;
while(res.next())
	
{
	String account=res.getString(1);
	 balance=res.getDouble(2);
	String sta=res.getString(3);
	String dt=res.getString(4);
	double tb=res.getDouble(5);
	String username=res.getString(6);
i++;
	%>
	
	<table>
	<body bgcolor="cyan">
	<tr><td>Account</td><td><%=account%></td></tr>
	<tr><td>Amount</td><td><%=balance%></td></tr>
	<tr><td>Status</td><td><%=sta%></td></tr>
	<tr><td>date_time</td><td><%=dt%></td></tr>
	<tr><td>Total_balance</td><td><%=tb%></td></tr>
	<tr><td>username</td><td><%=username%></td></tr>
	
	</body>
	</table>
	<hr>

<%
}
	if(i==0)
		
	{
		RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
		rd.include(request, response);
		out.println("<center><h1> you have no transaction yet </h1></center>");

		
	}
	
	}catch (Exception e) {
	e.printStackTrace();
}
%>



</body>
</html>