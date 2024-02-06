package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.Expense;
import com.soma.spenzy.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Set<Expense> findExpensesByUser_Email(String email);
    Set<Expense> findExpensesByUser_EmailAndExpenseType(String email, ExpenseType expenseType);
    Set<Expense> findExpensesByUser_EmailAndDateBetween(String email, LocalDateTime start, LocalDateTime end);
}
