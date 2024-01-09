package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Set<Subscription> getSubscriptionsByUserId(Long userId);
    Set<Subscription> getSubscriptionsByUserIdAndDateToPayBefore(Long userId, LocalDateTime date);
    Set<Subscription> getSubscriptionsByUserIdAndDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
