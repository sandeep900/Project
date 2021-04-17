

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Register
 */
@WebServlet("/regis")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=(int)(Math.random()*100+1);
		String username=request.getParameter("uname");
		String pass=Password.getPass();
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String fullname=fname+" "+lname;
		String gender=request.getParameter("gender");
		String mob=request.getParameter("mob");
		String email=request.getParameter("email");
		String adhaar=request.getParameter("adhaar");
	    String acno="12345"+((int)(Math.random()*1000));
	   // int acno=(int)(Math.random()*10000000+1);
	   
	    
	    
		double balance=0.0;
		
		
		try {
		
		Connection cn=DBAccess.getConn();
		
		PreparedStatement ps=cn.prepareStatement("insert into bankingapp values(?,?,?,?,?,?,?,?,?,?)");
		
		
		ps.setInt(1,id);
		ps.setString(2, fullname);
		ps.setString(3, username);
		
		ps.setString(4,pass);
		ps.setString(5,mob);
		
		ps.setString(6,gender);
		ps.setString(7,email);
		ps.setString(8,adhaar);
		ps.setString(9,acno);
		ps.setDouble(10,balance);    
	
	int a=ps.executeUpdate(); 
	
	

	
	
	
	PreparedStatement ps1=cn.prepareStatement("insert into profile values(?,?,?,?,?,?,?,?,?)");
	ps1.setInt(1,id);
	ps1.setString(2, fullname);
	ps1.setString(3, username);
	
	ps1.setString(4,pass);
	ps1.setString(5,mob);
	
	ps1.setString(6,gender);
	ps1.setString(7,email);
	ps1.setString(8,adhaar);
	ps1.setString(9,acno);
	//ps1.setDouble(10,balance);
	
	int b=ps1.executeUpdate();

	
	
	
	
		if(a>0)
		{
			String msg="Welcome Mr."+fullname+" your Account No. is "+acno+"\n Password is "+pass;
			try {
				Mail.sendEmail("sandeepmahapatra01@outlook.com", "Sandy@95560", email, "Welcome to JT Bank", msg);
			} 
			
				catch (Exception e) {
				e.printStackTrace();
			}
			
			
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		} catch (Exception e) {
			
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
			{
				out.print("<center><h1>user already exists</h1></center>");
			}
		e.printStackTrace();
		}
	}

}
