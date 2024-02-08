package com.soma.spenzy.service.userservice;

import com.soma.spenzy.model.DTO.UserDTO;


import java.util.Set;

public interface UserService {
    Set<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String token);
    UserDTO addNewUser(UserDTO userDTO);
    UserDTO updateUser(String token, UserDTO userDTO);
    void deleteUser(String token);
}
