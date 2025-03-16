package in.cricguru.controller;

import in.cricguru.dto.ContactFormDto;
import in.cricguru.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, Boolean>> sendContactForm(@RequestBody ContactFormDto contactForm) {
        emailService.sendContactFormEmail(contactForm);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

}