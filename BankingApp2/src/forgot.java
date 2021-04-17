

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forgot")
public class forgot extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email=request.getParameter("t1");
		
	try {
		Connection cn=DBAccess.getConn();
		PreparedStatement ps=cn.prepareStatement("select * from bankingapp where email=(?)");
		ps.setString(1,email);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			String pass=Password.getPass();
			ps.setString(1,email);
			
			System.out.print("hello");
			PreparedStatement ps1=cn.prepareStatement("update bankingapp set pass=(?) where email=(?)");
			
			ps1.setString(1,pass);
			ps1.setString(2,email);
			System.out.print("hello3");
			 PreparedStatement ps2=cn.prepareStatement("update profile set pass=(?) where email=(?)");
			ps2.setString(1,pass);
			ps2.setString(2,email);
			
			try {
				Mail.sendEmail("sandeepmahapatra01@outlook.com","Sandy@95560",email,"Your new Password","Your new password is"+pass);
				int a=ps1.executeUpdate();
				int b=ps2.executeUpdate();
				if(a>=1)
				{   
					out.println("<h1>your password is updated</h1>");
				
					RequestDispatcher rd=request.getRequestDispatcher("Loginpage.jsp");
				    rd.include(request, response);
				
				//	out.println("<h1>Your password is Updated</h1>");
				}
				//out.println("<h1>your password is updated</h1>");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}catch(Exception ee)
	{
		ee.printStackTrace();
	}
	
	
	}

}
