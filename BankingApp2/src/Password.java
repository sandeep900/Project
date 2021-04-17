import java.util.Random;

public class Password {

	public static String getPass() {
	String cap="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String small="abcdefghijklmnopqrstuvwxyz";
	String num="0123456789";
	String sym="%#@&";
	
	String all=cap+small+num+sym;
	
	char c[]=new char[6];
	Random r=new Random();
	
	for (int i = 0; i < c.length; i++) 
	{
		c[i]=all.charAt(r.nextInt(all.length()));
		
	}
	String pass=new String(c);
	System.out.println(pass);
	return pass;
}
}
