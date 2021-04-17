

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


@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	
       
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{
    		
    		HttpSession hs=request.getSession();
    		String name = (String)hs.getAttribute("name");//for username set
    		response.setContentType("text/html");
    		PrintWriter out=response.getWriter();
    		String acno=request.getParameter("t1");
    		double balance=Double.parseDouble(request.getParameter("t2"));
    		
    		

    		
    		
    		try 
    		{
    			Connection cn=DBAccess.getConn();
			PreparedStatement ps=cn.prepareStatement("select balance from bankingapp where acno=(?)");
			ps.setString(1,acno);
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				int availbalance=res.getInt(1);
				double depositbalance=availbalance+balance;
				PreparedStatement ps1=cn.prepareStatement ("update bankingapp set balance=(?) where acno=(?)");
				ps1.setDouble(1,depositbalance);
				ps1.setString(2,acno);
				int i=ps1.executeUpdate();
				
				String date_time=Date1.gettime();
				PreparedStatement ps2=cn.prepareStatement("insert into ministatement1 values(?,?,?,?,?,?)");
			    ps2.setString(1,acno);
				ps2.setDouble(2,balance);	
			    ps2.setString(3,"credit");
			    ps2.setString(4,date_time);
			    ps2.setDouble(5,depositbalance);
			    ps2.setString(6,name);
			  
				
				int k=ps2.executeUpdate();
				
				
				
				if(i>0)
				{
					RequestDispatcher req1=request.getRequestDispatcher("Home.jsp");
				req1.include(request, response);;
				
					out.println("<center>Your Deposited Amount is"+balance+"</center>");
				}
				
			}
			else
			{
				RequestDispatcher req=request.getRequestDispatcher("deposit.jsp");
				req.include(request, response);;
				out.println("<center>!!!Plz enter valid Accountnumber !!!</center>");
			}
			
			} catch (Exception e) 
    		{
				e.printStackTrace();
			}
		
    	}

}
