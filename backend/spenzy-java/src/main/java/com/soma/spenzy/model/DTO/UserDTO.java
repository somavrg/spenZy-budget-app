package com.soma.spenzy.model.DTO;

import java.util.Set;

public record UserDTO(
        String name,
        String email,
        Set<IncomeDTO> incomes,
        Set<ExpenseDTO> expenses,
        Set<SubscriptionDTO> subscriptions) {
}
