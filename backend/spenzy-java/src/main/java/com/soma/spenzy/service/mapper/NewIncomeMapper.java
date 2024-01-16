package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.NewIncomeDTO;
import com.soma.spenzy.model.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewIncomeMapper {
    Income toIncome(NewIncomeDTO newIncomeDTO);
    NewIncomeDTO toNewIncomeDTO(Income income);
}
