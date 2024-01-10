package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Set<Subscription> findSubscriptionsByUserId(Long userId);
    Set<Subscription> findSubscriptionsByUserIdAndDateToPayBefore(Long userId, LocalDateTime date);
    Set<Subscription> findSubscriptionsByUserIdAndDateToPayBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
