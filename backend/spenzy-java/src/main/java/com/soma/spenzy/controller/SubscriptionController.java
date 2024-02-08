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
        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(token), HttpStatus.OK) ;
    }

    // ? is it good like that
    @GetMapping("/interval")
    public ResponseEntity<Set<SubscriptionDTO>> getAllSubscriptionsBetweenDates(@RequestHeader("Authorization") String token,
                                                                          @RequestBody LocalDateTime start,
                                                                          @RequestBody LocalDateTime end) {
        return new ResponseEntity<>(subscriptionService.getSubscriptionsBetweenDates(token, start, end), HttpStatus.OK) ;
    }

    @GetMapping("/before")
    public ResponseEntity<Set<SubscriptionDTO>> getAllSubscriptionsBeforeDate(@RequestHeader("Authorization") String token,
                                                                              @RequestBody LocalDateTime date) {
        return new ResponseEntity<>(subscriptionService.getSubscriptionsBeforeDate(token, date), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<NewSubscriptionDTO> addNewSubscription(@RequestHeader("Authorization") String token,
                                                                 @RequestBody NewSubscriptionDTO newSubscriptionDTO) {
        return new ResponseEntity<>(subscriptionService.addNewSubscription(token, newSubscriptionDTO), HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteSubscriptionById(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return HttpStatus.NO_CONTENT;
    }
}
