package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Set<Income> findIncomesByUserId(Long userId);
    Set<Income> findIncomesByUserIdAndDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
