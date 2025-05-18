package com.chronicktrack.chronictrack.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.service.ChronicConditionService;
import com.chronicktrack.chronictrack.service.UserService;

@RestController
@RequestMapping("/api/conditions")
public class ChronicConditionController {
    
private final ChronicConditionService conditionService;
private final UserService userService;

public ChronicConditionController(ChronicConditionService conditionService, UserService 
userService){
this.conditionService = conditionService;
this.userService = userService;

}

@PostMapping
public ResponseEntity<ChronicCondition> addCondition(
@RequestBody ChronicCondition condition, @AuthenticationPrincipal UserDetails userDetails){
User user = userService.findByEmail(userDetails.getUsername());
ChronicCondition saved = conditionService.addCondition(
condition.getConditionName(), condition.getDescription(), user);
return ResponseEntity.ok(saved);
}

@GetMapping
public ResponseEntity<List<ChronicCondition>> getUserConditions(@AuthenticationPrincipal 
UserDetails userDetails){
User user = userService.findByEmail(userDetails.getUsername());
List<ChronicCondition> conditions = conditionService.getConditionsByUser(user);
return ResponseEntity.ok(conditions);
}
}
