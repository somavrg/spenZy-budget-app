package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Set<Income> getIncomesByUserId(Long userId);
    Set<Income> getIncomesByUserIdAndDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
