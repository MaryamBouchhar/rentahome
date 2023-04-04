package com.fpt.rentahome.Helpers;

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
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true); // true indicates html
        
        emailSender.send(message);
    }
}
