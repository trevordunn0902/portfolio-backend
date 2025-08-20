package com.trevordunn.portfolio_backend.controller;

import com.trevordunn.portfolio_backend.model.ContactMessage;
import com.trevordunn.portfolio_backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    private final ContactMessageRepository repository;

    @Autowired
    public ContactMessageController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> submitContactMessage(@RequestBody ContactMessage message) {
        // Ensure submittedAt is set if not already (optional safety)
        if (message.getSubmittedAt() == null) {
            message.setSubmittedAt(java.time.LocalDateTime.now());
        }

        repository.save(message);
        return ResponseEntity.ok("Message received");
    }
}
