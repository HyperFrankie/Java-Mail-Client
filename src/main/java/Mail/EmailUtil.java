package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailUtil {

	public static Map<String, String> IMAPHosts = new HashMap();

	public static void init() {
		IMAPHosts.put("google.com", "imap.google.com");
		IMAPHosts.put("hotmail.com", "imap-mail.outlook.com");
		IMAPHosts.put("hotmail.nl", "imap-mail.outlook.com");
	}

	public static Session login(String loginMail, String loginPassword) {
		final String mail = loginMail;
		final String pass = loginPassword;

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail, pass);
			}
		};

		try {
			InetAddress inetHost = InetAddress.getByName(mail.substring(mail.indexOf("@") + 1));
			String hostName = inetHost.getHostName();

			Properties props = new Properties();
			props.put("mail.imap.host", (IMAPHosts.containsKey(hostName) ? IMAPHosts.get(hostName) : "imap." + hostName));
			props.put("mail.imap.port", "993");
			props.put("mail.imap.auth", "true");
			props.put("mail.imap.ssl.enable", "true");

			return Session.getInstance(props, auth);

//			Store store = session.getStore("imap");
//			try {
//				store.connect();
//			} catch (AuthenticationFailedException e) {
//				System.out.println("authentication failed");
//				System.out.println("message: " + e.getMessage());
//			}
//
//			System.out.println("Personal folder list:" + store.getPersonalNamespaces());
//			System.out.println("Shared folder list:" + store.getSharedNamespaces());
//
//			Folder emailFolder = store.getFolder("INBOX");
//			emailFolder.open(Folder.READ_ONLY);
//
//			System.out.println(emailFolder.getFullName());
//
//			Message[] messages = emailFolder.getMessages();
//			System.out.println("messages.length---" + messages.length);
//
//			Message message = messages[messages.length - 1];
//			System.out.println("---------------------------------");
//			System.out.println("Email Number " + (messages.length));
//			System.out.println("Received on: " + message.getReceivedDate());
//			System.out.println("Subject: " + message.getSubject());
//			System.out.println("From: " + message.getFrom()[0]);
//			System.out.println("Text: " + message.getContent().toString());

		} catch(UnknownHostException ex) {
			System.out.println("Unrecognized host");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getAccordingIMAPHost(String provider) {

	}

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body){
		try {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");
	      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
	      msg.setSubject(subject, "UTF-8");
	      msg.setText(body, "UTF-8");
	      msg.setSentDate(new Date());
	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);
	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}