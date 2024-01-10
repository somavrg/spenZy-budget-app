package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.ExpenseDTO;
import com.soma.spenzy.model.Expense;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDTO toExpenseDTO(Expense expense);
    Set<ExpenseDTO> toExpenseDTOs(Set<Expense> expenses);
    Expense toExpense(ExpenseDTO expenseDTO);
    Set<Expense> toExpenses(Set<ExpenseDTO> expenseDTOs);
}
