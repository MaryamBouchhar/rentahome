package com.fpt.rentahome.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;


@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender emailSender = javaMailSender();
    
    public void sendVerificationEmail(String toEmail, String verificationToken) {
        String subject = "Verify your email";
        String verificationLink = "http://localhost:5173/verify-email?token=" + verificationToken + "&email=" + toEmail;
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

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("abdelatiflaghjaj@gmail.com");
        mailSender.setPassword("pdrnuadvktlkzsmn");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
