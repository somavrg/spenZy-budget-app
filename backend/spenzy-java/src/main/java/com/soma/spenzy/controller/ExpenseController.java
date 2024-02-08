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
        return new ResponseEntity<>(expenseService.getAllExpenses(token), HttpStatus.OK) ;
    }

    // ? still question how it should look like
    @GetMapping("/type")
    public ResponseEntity<Set<ExpenseDTO>> getAllExpensesByExpenseType(@RequestHeader("Authorization") String token,
                                                                       @RequestBody  ExpenseType expenseType) {
        return new ResponseEntity<>(expenseService.getExpensesByExpenseType(token, expenseType), HttpStatus.OK) ;
    }

    @GetMapping("/interval")
    public ResponseEntity<Set<ExpenseDTO>> getAllExpensesBetweenDates(@RequestHeader("Authorization") String token,
                                                                      @RequestBody LocalDateTime start,
                                                                      @RequestBody LocalDateTime end) {
        return new ResponseEntity<>(expenseService.getExpensesBetweenDates(token, start, end), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<NewExpenseDTO> addNewExpense(@RequestHeader("Authorization") String token,
                                                       @RequestBody NewExpenseDTO newExpense) {
        return new ResponseEntity<>(expenseService.addNewExpense(token, newExpense), HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return HttpStatus.NO_CONTENT;
    }
}
