package com.soma.spenzy.service.expenseservice;

import com.soma.spenzy.model.DTO.ExpenseDTO;
import com.soma.spenzy.model.DTO.NewExpenseDTO;
import com.soma.spenzy.model.Expense;
import com.soma.spenzy.model.ExpenseType;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.repository.ExpenseRepository;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.ExpenseMapper;
import com.soma.spenzy.service.mapper.NewExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;
    private final NewExpenseMapper newExpenseMapper;
    private final JwtService jwtService;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository,
                              ExpenseMapper expenseMapper, NewExpenseMapper newExpenseMapper,
                              JwtService jwtService) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
        this.newExpenseMapper = newExpenseMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Set<ExpenseDTO> getAllExpenses(String token) {
        return expenseMapper.toExpenseDTOs(expenseRepository
                .findExpensesByUser_Email(jwtService.extractUsername(token.split(" ")[1])));
    }

    // ? filter endpoints we will see how to implement
    @Override
    public Set<ExpenseDTO> getExpensesByExpenseType(String token, ExpenseType expenseType) {
        return expenseMapper.toExpenseDTOs(expenseRepository.findExpensesByUser_EmailAndExpenseType(
                jwtService.extractUsername(token.split(" ")[1]), expenseType));
    }

    // ? filter endpoints we will see how to implement
    @Override
    public Set<ExpenseDTO> getExpensesBetweenDates(String token, LocalDateTime start, LocalDateTime end) {
        return expenseMapper.toExpenseDTOs(expenseRepository.findExpensesByUser_EmailAndDateBetween(
                jwtService.extractUsername(token.split(" ")[1]), start, end
        ));
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public NewExpenseDTO addNewExpense(NewExpenseDTO newExpenseDTO) {
        SpenzyUser user = userRepository.findById(newExpenseDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "ERROR: User with [%s] id not found.".formatted(newExpenseDTO.userId())
                ));

        Expense newExpense = newExpenseMapper.toExpense(newExpenseDTO);
        newExpense.setUser(user);

        expenseRepository.save(newExpense);

        return newExpenseDTO;
    }
}
