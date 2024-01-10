package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Expense;
import com.soma.spenzy.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Set<Expense> findExpensesByUserId(Long userId);
    Set<Expense> findExpensesByUserIdAndExpenseType(Long userId, ExpenseType expenseType);
    Set<Expense> findExpensesByuserIdAndDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
