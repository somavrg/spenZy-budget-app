package com.soma.spenzy.model.DTO;

import java.time.LocalDateTime;

public record NewSubscriptionDTO(
        String name,
        int amount,
        LocalDateTime datePaid,
        LocalDateTime dateToPay,
        Long userId) {
}
