package com.soma.spenzy.service.userservice;

import com.soma.spenzy.model.DTO.UserDTO;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(
                userMapper.toUserDTOs(new HashSet<>(userRepository.findAll())),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(String token) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getUserByEmail(String email) {
        SpenzyUser spenzyUser = userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("No such user"));

        return new ResponseEntity<>(userMapper.toUserDTO(spenzyUser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> addNewUser(UserDTO newUserDTO) {
        SpenzyUser newUser = userMapper.toSpenzyUser(newUserDTO);
        userRepository.save(newUser);

        return new ResponseEntity<>(newUserDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(String token, UserDTO userDTO) {
        return null;
    }

    @Override
    public HttpStatus deleteUser(String token) {
        return null;
    }
}
