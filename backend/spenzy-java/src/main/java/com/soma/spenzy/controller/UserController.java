package com.soma.spenzy.controller;

import com.soma.spenzy.model.DTO.UserDTO;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.UserMapper;
import com.soma.spenzy.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader("Authorization") String token) {
        return userService.getUserById(token);
    }

    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token,
                                              @RequestBody UserDTO userUpdate) {
        return userService.updateUser(token, userUpdate);
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestHeader("Authorization") String token) {
        return userService.deleteUser(token);
    }
}
