import java.sql.Connection;
import java.sql.DriverManager;

public class DBAccess {

	static Connection cn;
	
	public static Connection getConn() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system123");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return cn;
	}

}
