

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

/**
 * Servlet implementation class transfer1
 */
@WebServlet("/transfer1")
public class transfer1 extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		double bal=0;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		Cookie ck[]=req.getCookies();
		String name="";
	
	/*
		if(ck!=null)
		{
			for(int i=0;i<ck.length;i++)
			{
				name=ck[i].getValue();
				
			}
			if(name.length()<10)
			{
				System.out.println("name is"+name);
			}
		
*/
		HttpSession hs=req.getSession();
		name=(String)hs.getAttribute("name");
		String acno2=req.getParameter("t2");
		double amount=Double.parseDouble(req.getParameter("t3"));

	
		try
		{
		 Connection cn=DBAccess.getConn();
	
			PreparedStatement st=cn.prepareStatement("select balance from bankingapp where USERNAME=(?)");
			st.setString(1,name);
			
			 
			System.out.println(name);
			
		     ResultSet rs=st.executeQuery();
		     
	
		if(rs.next())
		{
			
		bal=rs.getDouble(1);
		//double price=rs.getDouble(2);
		System.out.println(amount+"\t"+bal);
		}
		
		if(bal>=amount)
		{	
			PreparedStatement ps=cn.prepareStatement("update bankingapp set balance=balance-(?) where  username=(?)");
			
			ps.setDouble(1, amount);
			ps.setString(2, name);
			
			
			PreparedStatement ps1=cn.prepareStatement("update bankingapp set balance=balance+(?) where acno=(?)");
			ps1.setDouble(1, amount);
			ps1.setString(2, acno2);
			
		int k =ps.executeUpdate();
		int l =ps1.executeUpdate();
	    System.out.println("k is"+k);
	    System.out.println("l is"+l);
		
        double availbalance=bal-amount;
		String date_time=Date1.gettime();
		PreparedStatement ps2=cn.prepareStatement("insert into ministatement1 values(?,?,?,?,?,?)");
		 ps2.setString(1,acno2);
		ps2.setDouble(2,amount);	
	    ps2.setString(3,"transfer by");
	    ps2.setString(4,date_time);
	   ps2.setDouble(5,availbalance);
	   ps2.setString(6,name);
		
		int m=ps2.executeUpdate();
		
       if(k>0 & l>0)
		{
		out.println("<center>Trasaction succesfully completed</center>");
		RequestDispatcher rd=req.getRequestDispatcher("Home.jsp");
		rd.include(req,res);
		}
		else
		{
		out.println("<center> Transaction unsuccessfull </center>");
		PreparedStatement ps3=cn.prepareStatement("update bankingapp set balance=balance+(?) where  username=(?)");
		ps3.setString(2, name);
		ps3.setDouble(1, amount);
		
		int n=ps3.executeUpdate();
		
		RequestDispatcher rd =req.getRequestDispatcher("Home.jsp");
		rd.include(req,res);
		 }
		}
		else
		{
		out.println("<center>Insufficient balance</center>");
		RequestDispatcher rd=req.getRequestDispatcher("transfer.jsp");
		rd.include(req,res);
		}
		}
		
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
		}
	

}









