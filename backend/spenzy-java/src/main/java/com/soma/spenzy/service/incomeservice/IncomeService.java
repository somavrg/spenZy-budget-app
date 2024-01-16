package com.soma.spenzy.service.incomeservice;

import com.soma.spenzy.model.DTO.IncomeDTO;
import com.soma.spenzy.model.DTO.NewIncomeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface IncomeService {
    ResponseEntity<Set<IncomeDTO>> getAllIncomes(String token);
    ResponseEntity<Set<IncomeDTO>> getIncomesBetweenDates(String token, LocalDateTime start, LocalDateTime end);
    HttpStatus deleteIncome(Long incomeId);
    ResponseEntity<NewIncomeDTO> addNewExpense(NewIncomeDTO newIncomeDTO);
}
