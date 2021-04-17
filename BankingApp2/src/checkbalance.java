

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/balance")
public class checkbalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text.html");
		PrintWriter out=response.getWriter();
		    		
		try 
		{
			Connection cn=DBAccess.getConn();
			PreparedStatement ps=cn.prepareStatement("select balance from bankingapp where username=(?)");
			HttpSession session=request.getSession(false);
			String name=(String) session.getAttribute("name");
			System.out.println(name);
			ps.setString(1, name);
			ResultSet res=ps.executeQuery();
		if(res.next())
		{
		
			PreparedStatement ps1=cn.prepareStatement("select balance from bankingapp where username=(?)");
			ps1.setString(1, name);
			
			out.println("<body><font color='yellow' size='6'><center>Your Total balance is"+res.getString(1)+"</center></font></body>");
				RequestDispatcher req1=request.getRequestDispatcher("Home.jsp");
			req1.include(request, response);;
			
				
			}
			
		
					} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		
	}

}
