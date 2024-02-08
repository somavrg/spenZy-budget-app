package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Expense;
import com.soma.spenzy.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Set<Expense> findAllByUser_Email(String email);
    Set<Expense> findAllByUser_EmailAndExpenseType(String email, ExpenseType expenseType);
    Set<Expense> findAllByUser_EmailAndDateBetween(String email, LocalDateTime start, LocalDateTime end);
}
