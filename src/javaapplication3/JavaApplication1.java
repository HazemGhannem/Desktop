/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

import entities.*;
import java.util.List;
import services.EventService;
import entities.Event;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String to = "marweenkoui.98@gmail.com"; // to address. It can be any like gmail, hotmail etc.
        final String from = "marwen.kouidhi@esprit.tn"; // from address. As this is using Gmail SMTP.
        final String password = "XDgnJ@?559"; // password for from mail address.

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);

        try {
            for (int i = 0; i < 3; i++) {

                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject("Canceled Event!");
                message.setText("dddddddd");
                Transport.send(message);

                System.out.println("Mail successfully sent..");
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        List l = new ArrayList();
//        A a = new A("marwen0");
//        A b = new A("marwen0");
//        A kk = new A("kkkkkkkkkkkkkk");
//        A c = new A("marwen1");
//        A d = new A("marwen2");
//        A e = new A("marwen3");
//        l.add(a);
//        l.add(b);
//        l.add(c);
//        l.add(d);
//        l.add(e);
//
//        l.set(l.indexOf(a), kk);
//
//        System.out.println(l.get(0));
//        EventService es = new EventService();
//        Event e = new Event(-1);
//        e.setName("javafx");
//        e.setDescription("javafx");
//        e.setDate(new Date(2022, 10, 10));
//        e.setTime(new Time(10, 00, 00));
//        e.setRestaurant(1);
//        e.setImage("image");
//        e.setMaxReservations(10);
//        e.setPrice(11);
//        e.setNbReservation(0);
//
//        es.ajouter(e);
//
//        //        List<Event> l = es.recuperer();
//        //
//        //        for (int i = 0; i < l.size(); i++) {
//        //            System.out.println(l.get(i));
//        //        }
//        //        System.out.println("*****************************");
//        //
//        //        Event e = l.get(0);
//        //        e.setPrice(11111);
//        //        es.modifier(e);
//        //
//        //        e.setName("event1 duplicated");
//        //        es.ajouter(e);
//        //
//        //        for (int i = 0; i < l.size(); i++) {
//        //            System.out.println(l.get(i));
//        //        }
//        //
//        //        System.out.println(e.getReservations());
    }

}
