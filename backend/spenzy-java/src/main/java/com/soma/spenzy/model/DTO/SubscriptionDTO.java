package com.soma.spenzy.model.DTO;

import java.time.LocalDateTime;

public record SubscriptionDTO(
        String name,
        int amount,
        LocalDateTime datePaid,
        LocalDateTime dateToPay) {
}
