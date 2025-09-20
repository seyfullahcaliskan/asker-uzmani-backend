package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Services.PayTRService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(Paths.PAYTR)
public class PayTRController {

    @Autowired
    private PayTRService paytrService;

    @PostMapping("/get-token")
    public ResponseEntity<Map<String, String>> getIframeToken(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(paytrService.generateToken(payload, request));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/notification")
    public ResponseEntity<String> notification(@RequestParam Map<String, String> post) {
        try {
            return paytrService.handleNotification(post);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }
    }
}
