package com.soma.spenzy.controller;

import com.soma.spenzy.model.DTO.UserDTO;
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
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(userService.getUserByEmail(token), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token,
                                              @RequestBody UserDTO userUpdate) {
        return new ResponseEntity<>(userService.updateUser(token, userUpdate), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestHeader("Authorization") String token) {
        userService.deleteUser(token);
        return HttpStatus.NO_CONTENT;
    }
}
