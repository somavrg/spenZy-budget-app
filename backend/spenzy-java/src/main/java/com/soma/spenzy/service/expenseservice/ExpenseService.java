package com.soma.spenzy.service.expenseservice;

import com.soma.spenzy.model.DTO.ExpenseDTO;
import com.soma.spenzy.model.DTO.NewExpenseDTO;
import com.soma.spenzy.model.ExpenseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExpenseService {
    ResponseEntity<Set<ExpenseDTO>> getAllExpenses(String token);
    ResponseEntity<Set<ExpenseDTO>> getExpensesByExpenseType(String token, ExpenseType expenseType);
    ResponseEntity<Set<ExpenseDTO>> getExpensesBetweenDates(String token, LocalDateTime start, LocalDateTime end);
    HttpStatus deleteExpense(Long expenseId);
    ResponseEntity<ExpenseDTO> addNewExpense(NewExpenseDTO newExpenseDTO);
}
