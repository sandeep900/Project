

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/abc")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("t1");
		String pass=request.getParameter("t2");
		
		try {
			Connection cn=DBAccess.getConn();
			PreparedStatement ps=cn.prepareStatement("select * from bankingapp where username=(?) and pass=(?) ");
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				HttpSession ss=request.getSession();
				ss.setAttribute("name", name);// for etc
				HttpSession hs=request.getSession();
				name=(String)hs.getAttribute("name");//for get session
				
				Cookie ck=new Cookie("name", name);

				ck.setMaxAge(60*60*24);
				response.addCookie(ck);
				
				RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}else {
				out.println("<h2>Invalid Username and Password </h2>");
				out.println("<a href=forgetpassword.jsp>forgetpassword</a>");
				RequestDispatcher rd=request.getRequestDispatcher("Loginpage.jsp");
				rd.include(request, response);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
