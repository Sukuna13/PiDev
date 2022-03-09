/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.CourCoach;
import java.util.Properties;
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
 *
 * @author Asus
 */
public class JavaMail {

    public static void sendMail(String recipient, String type, CourCoach cour) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String myAccountEmail = "ibrahim.reguigui@esprit.tn";
        String password = "213JMT1808";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recipient, type, cour);
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String type, CourCoach cour) {
        if (type == "annulationReservationParCoach") {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject("Reservation Annulée");
                message.setText("Nous somme désolé de vous informé que votre reservation du " + cour.getDate() + " à " + cour.getTime() + " a été annulée");
                return message;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else if (type == "demandeReservationRefusée") {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject("Reservation Refusée");
                message.setText("Nous somme désolé de vous informé que votre reservation du " + cour.getDate() + " à " + cour.getTime() + " a été refusée");
                return message;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }else if (type == "demandeReservationAcceptée") {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject("Reservation Acceptée");
                message.setText("Votre reservation du " + cour.getDate() + " à " + cour.getTime() + " a été acceptée");
                return message;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }else if (type == "reservationAnnuléeParSportif") {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject("Reservation Annulée");
                message.setText("La reservation du " + cour.getDate() + " à " + cour.getTime() + " a été annulée");
                return message;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return null;
    }

//    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject("mail tessstt subject!");
//            message.setText("Tesssssssssssttttt message");
//            return message;
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//        return null;
//    }
}
