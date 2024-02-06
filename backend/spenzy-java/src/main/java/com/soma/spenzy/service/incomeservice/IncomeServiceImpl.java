package com.soma.spenzy.service.incomeservice;

import com.soma.spenzy.model.DTO.IncomeDTO;
import com.soma.spenzy.model.DTO.NewIncomeDTO;
import com.soma.spenzy.model.Income;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.repository.IncomeRepository;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.IncomeMapper;
import com.soma.spenzy.service.mapper.NewIncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final IncomeMapper incomeMapper;
    private final NewIncomeMapper newIncomeMapper;
    private final JwtService jwtService;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository, UserRepository userRepository,
                             IncomeMapper incomeMapper, NewIncomeMapper newIncomeMapper, JwtService jwtService) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
        this.incomeMapper = incomeMapper;
        this.newIncomeMapper = newIncomeMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Set<IncomeDTO> getAllIncomes(String token) {
        return incomeMapper.toIncomeDTOs(incomeRepository
                .findIncomesByUser_Email(jwtService.extractUsername(token.split(" ")[1])));
    }

    @Override
    public Set<IncomeDTO> getIncomesBetweenDates(String token, LocalDateTime start, LocalDateTime end) {
        return incomeMapper.toIncomeDTOs(incomeRepository
                .findIncomesByUser_EmailAndDateBetween(
                        jwtService.extractUsername(token.split(" ")[1]), start, end));
    }

    @Override
    public void deleteIncome(Long incomeId) {
        incomeRepository.deleteById(incomeId);
    }

    @Override
    public NewIncomeDTO addNewIncome(NewIncomeDTO newIncomeDTO) {
        SpenzyUser user = userRepository.findById(newIncomeDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "ERROR: User with [%s] id not found.".formatted(newIncomeDTO.userId())
                ));

        Income newIncome = newIncomeMapper.toIncome(newIncomeDTO);
        newIncome.setUser(user);

        incomeRepository.save(newIncome);

        return newIncomeDTO;
    }
}
