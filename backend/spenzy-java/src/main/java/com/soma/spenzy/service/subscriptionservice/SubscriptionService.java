package com.soma.spenzy.service.subscriptionservice;

import com.soma.spenzy.model.DTO.NewSubscriptionDTO;
import com.soma.spenzy.model.DTO.SubscriptionDTO;

import java.time.LocalDateTime;
import java.util.Set;

public interface SubscriptionService {
    Set<SubscriptionDTO> getAllSubscriptions(String token);
    Set<SubscriptionDTO> getSubscriptionsBetweenDates(String token, LocalDateTime start, LocalDateTime end);
    Set<SubscriptionDTO> getSubscriptionsBeforeDate(String token, LocalDateTime date);
    NewSubscriptionDTO addNewSubscription(String token, NewSubscriptionDTO newSubscriptionDTO);
    void deleteSubscription(Long subscriptionId);
}
