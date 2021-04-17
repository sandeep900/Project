

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ministatement")
public class ministatement extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{       
		
          response.setContentType("text/html");
          PrintWriter out=response.getWriter();
          HttpSession session=request.getSession(false);
		String name=(String) session.getAttribute("name");
		
          try 
		{
		Connection cn=DBAccess.getConn();
		PreparedStatement ps=cn.prepareStatement("select * from ministatement1 where username=(?)");
		System.out.println(name);
		ps.setString(1, name);
		ResultSet res=ps.executeQuery();
		System.out.println("hello");
		
		if(res.next())
		{
			
			
		
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
}
}
		
		
		
		

		