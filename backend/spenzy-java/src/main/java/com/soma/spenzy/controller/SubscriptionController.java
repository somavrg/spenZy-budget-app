package com.soma.spenzy.controller;

import com.soma.spenzy.model.DTO.NewSubscriptionDTO;
import com.soma.spenzy.model.DTO.SubscriptionDTO;
import com.soma.spenzy.service.subscriptionservice.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<Set<SubscriptionDTO>> getAllSubscriptions(@RequestHeader("Authorization") String token) {
        return subscriptionService.getAllSubscriptions(token);
    }

    @GetMapping("/interval")
    public ResponseEntity<Set<SubscriptionDTO>> getAllSubscriptionsBetweenDates(@RequestHeader("Authorization") String token,
                                                                          @RequestBody LocalDateTime start,
                                                                          @RequestBody LocalDateTime end) {
        return subscriptionService.getSubscriptionsBetweenDates(token, start, end);
    }

    @PostMapping
    public ResponseEntity<NewSubscriptionDTO> addNewSubscription(@RequestBody NewSubscriptionDTO newSubscriptionDTO) {
        return subscriptionService.addNewSubscription(newSubscriptionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscriptionById(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }
}
