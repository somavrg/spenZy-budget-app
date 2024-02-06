package com.soma.spenzy.service.expenseservice;

import com.soma.spenzy.model.DTO.ExpenseDTO;
import com.soma.spenzy.model.DTO.NewExpenseDTO;
import com.soma.spenzy.model.ExpenseType;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExpenseService {
    Set<ExpenseDTO> getAllExpenses(String token);
    Set<ExpenseDTO> getExpensesByExpenseType(String token, ExpenseType expenseType);
    Set<ExpenseDTO> getExpensesBetweenDates(String token, LocalDateTime start, LocalDateTime end);
    void deleteExpense(Long expenseId);
    NewExpenseDTO addNewExpense(NewExpenseDTO newExpenseDTO);
}
