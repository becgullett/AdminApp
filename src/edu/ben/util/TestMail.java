package edu.ben.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class TestMail {

private static String USER_NAME = "buwebhound";  // GMail user name (just the part before "@gmail.com")
private static String PASSWORD = "PuppyChow#17"; // GMail password

private static String RECIPIENT = "jjancek12@gmail.com";

public static void main(String[] args) {
    String from = USER_NAME;
    String pass = PASSWORD;
    String[] to = { RECIPIENT }; // list of recipient email addresses
    String subject = "Login Credentials for BUWebhound";
    String body = "Hello User!" + "\n\n" + "Below you will be able to view your login credentials for "
    		+ "your BUWebhound account." + "\n\n" + "Login: " + "String for user login\n" + "Password: " 
    		+ "String for user password\n\n" + "Thanks for using our automated grading system!\n\n" 
    		+ "Sincerely,\n\n" + "The Webhound Development Team\n" + "buwebhound@gmail.com";

    sendFromGMail(from, pass, to, subject, body);
}

public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
    Properties props = System.getProperties();
    String host = "smtp.gmail.com";

    props.put("mail.smtp.starttls.enable", "true");

    props.put("mail.smtp.ssl.trust", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");


    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        // To get the array of addresses
        for( int i = 0; i < to.length; i++ ) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for( int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject(subject);
        message.setText(body);

        Transport transport = session.getTransport("smtp");

        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("Message sent Successfully");
        transport.close();

    }
    catch (AddressException ae) {
        ae.printStackTrace();
        System.out.println("Failed to send message");
    }
    catch (MessagingException me) {
        me.printStackTrace();
        System.out.println("Failed to send message");
    }
    }
   } 