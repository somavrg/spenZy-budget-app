package com.soma.spenzy.service.userservice;

import com.soma.spenzy.model.DTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface UserService {
    ResponseEntity<Set<UserDTO>> getAllUsers();
    ResponseEntity<UserDTO> getUserById(String token);
    ResponseEntity<UserDTO> getUserByEmail(String email);
    ResponseEntity<UserDTO> addNewUser(UserDTO userDTO);
    ResponseEntity<UserDTO> updateUser(String token, UserDTO userDTO);
    HttpStatus deleteUser(String token);
}
