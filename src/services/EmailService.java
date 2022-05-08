/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import entities.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author user01
 */
public class EmailService {

    public static void send(String from, String password, Event e) {
        List<Reservation> reservations = e.getReservations();
        String to = "marweenkoui.98@gmail.com"; // to address. It can be any like gmail, hotmail etc.
        final String fromx = "marwen.kouidhi@esprit.tn"; // from address. As this is using Gmail SMTP.
        final String passwordx = "XDgnJ@?559"; // password for from mail address.

        String msg = "We are sorry to inform you that the evnet : " + e.getName() + " that was planned on " + e.getDate() + " at " + e.getTime() + " was canceled";;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromx, passwordx);
            }
        });
        Message message = new MimeMessage(session);

        try {
            for (int i = 0; i < reservations.size(); i++) {

                message.setFrom(new InternetAddress(fromx));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reservations.get(i).getEmail()));
                message.setSubject("Canceled Event!");
                message.setText(msg);
                Transport.send(message);
                System.out.println(msg);

            }
        } catch (MessagingException err) {
            err.printStackTrace();
        }
    }
}
