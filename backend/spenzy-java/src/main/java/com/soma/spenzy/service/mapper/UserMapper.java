package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.UserDTO;
import com.soma.spenzy.model.SpenzyUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(uses = {ExpenseMapper.class, IncomeMapper.class, SubscriptionMapper.class}, componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    UserDTO toUserDTO(SpenzyUser spenzyUser);
    Set<UserDTO> toUserDTOs(Set<SpenzyUser> spenzyUsers);
    SpenzyUser toSpenzyUser(UserDTO userDTO);
    Set<SpenzyUser> toSpenzyUsers(Set<UserDTO> userDTOs);
}
