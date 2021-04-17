import java.text.SimpleDateFormat;
import java.util.Date;

public class Date1 {
  public static String gettime()
{
	SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yy hh:mm:ss a");
	Date d=new Date();
	String time=sdf.format(d);
	System.out.println("date & time is "+ time);
	return time;
}
}

