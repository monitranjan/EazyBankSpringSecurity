package com.monit.Controller;

import com.monit.model.Customer;
import com.monit.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer user) {
        try {
            String hashPwd = passwordEncoder.encode(user.getPwd());
            user.setPwd(hashPwd);
            Customer savedCustomer = customerRepository.save(user);
            if (savedCustomer.getId() > 0) {
                return ResponseEntity.ok("User registered successfully");
            } else {
                return ResponseEntity.badRequest().body("User registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An exception occured" + e.getMessage());
        }
    }
}
