// src/main/java/com/trevordunn/portfolio_backend/controller/AdminController.java

package com.trevordunn.portfolio_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // allow frontend dev server
public class AdminController {

    @GetMapping("/api/admin/check")
    public String checkAdmin() {
        return "OK"; // if credentials are valid, this will return
    }
}
