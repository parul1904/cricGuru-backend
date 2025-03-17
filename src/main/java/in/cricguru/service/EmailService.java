package in.cricguru.service;

import in.cricguru.dto.ContactFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String RECIPIENT_EMAIL = "help.cricguru@gmail.com";

    public void sendContactFormEmail(ContactFormDto contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(RECIPIENT_EMAIL);
        message.setSubject(contactForm.getSubject());
        message.setText(contactForm.getMessage());
        message.setFrom(contactForm.getEmail());
        javaMailSender.send(message);
    }
}