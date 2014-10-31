package controller;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


import javax.mail.Message;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailSender {
    private static  void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress("no_reply@journaldev.com", "NoReply-JD"));
 
          msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));
 
          msg.setSubject(subject, "UTF-8");
 
          msg.setText(body, "UTF-8");
 
          msg.setSentDate(new Date());
 
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          Transport.send(msg);  
 
          System.out.println("Email Sent Successfully!!");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    public  void send(String toAddress,String content,String title) {
        final String fromEmail = "williamlin59@outlook.com"; //requires valid gmail id
        final String password = "56666493"; // correct password for gmail id
 
         
        Properties property = new Properties();
        property.put("mail.smtp.host", "smtp.live.com"); //SMTP Host
        property.put("mail.smtp.port", "587"); //TLS Port
        property.put("mail.smtp.auth", "true"); //enable authentication
        property.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
         
                //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(property, auth);
         
        sendEmail(session, toAddress,title,content);
         
    }
 
     
}


