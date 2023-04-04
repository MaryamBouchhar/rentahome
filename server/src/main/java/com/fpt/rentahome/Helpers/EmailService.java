package com.fpt.rentahome.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender emailSender;
    
    public void sendVerificationEmail(String toEmail, String verificationToken) {
        String subject = "Verify your email";
        String verificationLink = "http://yourapp.com/verify-email?token=" + verificationToken + "&email=" + toEmail;
        String body = "<p>Please click the following link to verify your email address:</p>" +
                      "<a href=\"" + verificationLink + "\">" + verificationLink + "</a>";
        
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true); // true indicates html
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            emailSender.send(message);
        }
    }
}
