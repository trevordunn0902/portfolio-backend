package com.trevordunn.portfolio_backend.controller;

import com.trevordunn.portfolio_backend.model.ContactMessage;
import com.trevordunn.portfolio_backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")  // allow requests from any origin (adjust for production)
public class ContactMessageController {

    @Autowired
    private ContactMessageRepository repository;

    @PostMapping
    public ResponseEntity<String> submitContactMessage(@RequestBody ContactMessage message) {
        repository.save(message);
        return ResponseEntity.ok("Message received");
    }
}
