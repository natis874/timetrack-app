package com.pfe.timetrack.controllers;

import com.pfe.timetrack.dtos.LoginRequestDto;
import com.pfe.timetrack.models.Employe;
import com.pfe.timetrack.repositories.IEmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private IEmployeRepository employeRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        // Find employe by email (used as username)
        Optional<Employe> employe = employeRepository.findByEmail(loginRequest.getEmail());
        // Very simple validation - in a real app, you'd use proper password hashing
        if (employe.isPresent() && employe.get().getPassword().equals(loginRequest.getPassword())) {
            // Create a simple response object
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("employe", employe);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Invalid email or password");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

}
