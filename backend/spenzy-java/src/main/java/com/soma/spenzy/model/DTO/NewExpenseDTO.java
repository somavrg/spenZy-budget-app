package com.soma.spenzy.model.DTO;

import com.soma.spenzy.model.ExpenseType;

import java.time.LocalDateTime;

public record NewExpenseDTO(
        String name,
        ExpenseType expenseType,
        int amount,
        LocalDateTime date,
        //optional?
        String info,
        Long userId) {
}
