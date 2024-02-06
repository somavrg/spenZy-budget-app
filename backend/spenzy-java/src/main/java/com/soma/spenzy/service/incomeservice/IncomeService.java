package com.soma.spenzy.service.incomeservice;

import com.soma.spenzy.model.DTO.IncomeDTO;
import com.soma.spenzy.model.DTO.NewIncomeDTO;

import java.time.LocalDateTime;
import java.util.Set;

public interface IncomeService {
    Set<IncomeDTO> getAllIncomes(String token);
    Set<IncomeDTO> getIncomesBetweenDates(String token, LocalDateTime start, LocalDateTime end);
    void deleteIncome(Long incomeId);
    NewIncomeDTO addNewIncome(NewIncomeDTO newIncomeDTO);
}
