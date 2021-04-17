import java.util.*;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class Mail {
    public static void sendEmail(final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,MessagingException {
 
       
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.live.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
        
 
        // sends the e-mail
        Transport.send(msg);
        System.out.println("Sent");
    }
  /*  public static void main(String[] args) {
	Scanner ss=new Scanner(System.in);
	System.out.println("Enter to Address");
    	String to=ss.nextLine();
	
	String fr="sandeepmahapatra01@outlook.com";
	String ps="Sandy@95560";
	System.out.println("Enter Subject");
    	String sub=ss.nextLine();
	System.out.println("Enter Message");
	String msg=ss.nextLine();
		try {
		sendEmail(fr,ps,to,sub,msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

}*/
}