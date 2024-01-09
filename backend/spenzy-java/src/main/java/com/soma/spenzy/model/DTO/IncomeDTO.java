package com.soma.spenzy.model.DTO;

import java.time.LocalDateTime;

public record IncomeDTO(
        String name,
        int amount,
        LocalDateTime date,
        //optional?
        String info) {
}
