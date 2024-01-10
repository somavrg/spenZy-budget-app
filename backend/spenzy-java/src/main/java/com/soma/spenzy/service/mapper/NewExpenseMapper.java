package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.NewExpenseDTO;
import com.soma.spenzy.model.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewExpenseMapper {
    Expense toExpense(NewExpenseDTO newExpenseDTO);
    NewExpenseDTO toNewExpenseDTO(Expense expense);
}
