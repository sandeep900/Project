package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.CoronaBo;

public class CoranaDaoImpl implements CoranaDao
{
	
	
	// sql query
	@Resource(name="Dsjndi")
	//private static final String DS_JNDI_NAME="java:/comp/env/Dsjndi";
private static final String INSERT_QUERY="INSERT INTO LAYERED_CORONA VALUES(sno_SEQ1.NEXTVAL,?,?,?,?)";
	Connection con=null;
	@Override
	public int insert(CoronaBo bo) throws Exception {
		
		//get pooled jdbc connection
		con=getpooledConnection();
		System.out.println("connection"+con);
//preparestatement
	
PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
ps.setString(1, bo.getPname()); 
ps.setInt(2, bo.getPage());
ps.setString(3, bo.getPaddrs());
ps.setString(4, bo.getPaddrs());
int count=ps.executeUpdate();
System.out.println("Executeupdate\t"+count);

return count;
	
	}
	private Connection getpooledConnection()throws Exception
	{
		//Connection con=null;
	

	 			
		InitialContext ic=null;
		DataSource ds=null;
		//Connection con=null;
		
		ic=new InitialContext();
		ds=(DataSource)ic.lookup("java:/comp/env/Dsjndi");
		System.out.println("Data source \t"+ds+"\n connection  "+con);

				con=ds.getConnection();
				System.out.println("connection class "+con.getClass());

		 /* try {
	 			Class.forName("oracle.jdbc.driver.OracleDriver");
	 			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system123");
	 		} catch (Exception e) {
	 		e.printStackTrace();
	 		}
*/		return con;
		
	}
	
	

}
