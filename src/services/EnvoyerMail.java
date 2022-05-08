/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author salim
 */
public class EnvoyerMail {
     
    public static void sendMail(String recepient, String body) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "mohamedyassine.ghazouani@esprit.tn";
        String password = "213JFT3100";
        
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
          Message message = prepareMessage(session, myAccountEmail,recepient,body); 
          
          Transport.send(message);
          System.out.println("Message sent successfuly");
        }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String body) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("You win a Coupon with FlyFood ❤️");
            message.setText(body);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EnvoyerMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
}
    
}
        

            
            
    

