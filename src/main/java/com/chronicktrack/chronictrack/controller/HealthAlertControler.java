package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.service.HealthAlertService;

@RestController
@RequestMapping("/api/alerts")
public class HealthAlertControler {
    
private final HealthAlertService healthAlertService;

public HealthAlertControler(HealthAlertService healthAlertService){
this.healthAlertService = healthAlertService;
}

@GetMapping("/{username}")
public ResponseEntity<List<String>> getUserAlerts(@PathVariable String username){
List<String> alerts = healthAlertService.generateAlertsForUser(username);
return ResponseEntity.ok(alerts);
}
}
