package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.IncomeDTO;
import com.soma.spenzy.model.Income;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    IncomeDTO toIncomeDTO(Income income);
    Set<IncomeDTO> toIncomeDTOs(Set<Income> incomes);
    Income toIncome(IncomeDTO incomeDTO);
    Set<Income> toIncomes(Set<IncomeDTO> incomeDTOs);
}
