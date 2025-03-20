package in.cricguru.controller;

import in.cricguru.dto.LoginDto;
import in.cricguru.entity.User;
import in.cricguru.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        LOGGER.info("Login request received for email: {}", loginDto.getEmail());
        User user = userService.findByEmail(loginDto.getEmail());
        if (user != null && user.getPassword().equals(loginDto.getPassword())
        && "admin".equalsIgnoreCase(user.getRole())) {
            LOGGER.info("Admin Logged in successfully");
            return ResponseEntity.ok("Admin");
        } else if (user != null && user.getPassword().equals(loginDto.getPassword())
                && "user".equalsIgnoreCase(user.getRole())) {
            LOGGER.info("User Logged in successfully");
            return ResponseEntity.ok("User");
        } else {
            LOGGER.info("Other Logged in successfully");
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}