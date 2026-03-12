package com.tictactoe.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

public class EmailUtil {

    public static void sendOTP(String toEmail, String otp) {

        final String fromEmail = "kgosukula@gmail.com";
        final String password = "xapa xnzj qsms igte";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject("TicTacToe OTP Verification");

            message.setText("Your OTP is: " + otp);

            Transport.send(message);

            System.out.println("OTP sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}