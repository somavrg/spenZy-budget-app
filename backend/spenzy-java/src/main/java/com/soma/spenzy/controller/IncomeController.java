package com.soma.spenzy.controller;

import com.soma.spenzy.model.DTO.IncomeDTO;
import com.soma.spenzy.model.DTO.NewIncomeDTO;
import com.soma.spenzy.service.incomeservice.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {this.incomeService = incomeService;}

    @GetMapping
    public ResponseEntity<Set<IncomeDTO>> getAllIncomes(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(incomeService.getAllIncomes(token), HttpStatus.OK);
    }

    @GetMapping("/interval")
    public ResponseEntity<Set<IncomeDTO>> getAllIncomesBetweenDates(@RequestHeader("Authorization") String token,
                                                                      @RequestBody LocalDateTime start,
                                                                      @RequestBody LocalDateTime end) {
        return new ResponseEntity<>(incomeService.getIncomesBetweenDates(token, start, end), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<NewIncomeDTO> addNewIncome(@RequestHeader("Authorization") String token,
                                                     @RequestBody NewIncomeDTO newIncomeDTO) {
        return new ResponseEntity<>(incomeService.addNewIncome(token, newIncomeDTO), HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteIncomeById(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return HttpStatus.NO_CONTENT;
    }
}
