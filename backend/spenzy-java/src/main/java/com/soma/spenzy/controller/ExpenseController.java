package com.soma.spenzy.controller;

import com.soma.spenzy.model.DTO.ExpenseDTO;
import com.soma.spenzy.model.DTO.NewExpenseDTO;
import com.soma.spenzy.model.ExpenseType;
import com.soma.spenzy.service.expenseservice.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {this.expenseService = expenseService;}

    @GetMapping
    public ResponseEntity<Set<ExpenseDTO>> getAllExpenses(@RequestHeader("Authorization") String token) {
        return expenseService.getAllExpenses(token);
    }

    // ?
    @GetMapping("/type")
    public ResponseEntity<Set<ExpenseDTO>> getAllExpensesByExpenseType(@RequestHeader("Authorization") String token,
                                                                       @RequestBody  ExpenseType expenseType) {
        return expenseService.getExpensesByExpenseType(token, expenseType);
    }

    @GetMapping("/interval")
    public ResponseEntity<Set<ExpenseDTO>> getAllExpensesBetweenDates(@RequestHeader("Authorization") String token,
                                                                      @RequestBody LocalDateTime start,
                                                                      @RequestBody LocalDateTime end) {
        return expenseService.getExpensesBetweenDates(token, start, end);
    }

    @PostMapping
    public ResponseEntity<NewExpenseDTO> addNewExpense(@RequestBody NewExpenseDTO newExpense) {
        return expenseService.addNewExpense(newExpense);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
