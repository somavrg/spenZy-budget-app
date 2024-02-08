package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Set<Subscription> findAllByUser_Email(String email);
    Set<Subscription> findAllByUser_EmailAndDateToPayBefore(String email, LocalDateTime date);
    Set<Subscription> findAllByUser_EmailAndDateToPayBetween(String email, LocalDateTime start, LocalDateTime end);
}
