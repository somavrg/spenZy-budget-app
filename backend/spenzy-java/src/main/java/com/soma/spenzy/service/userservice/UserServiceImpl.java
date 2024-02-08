package com.soma.spenzy.service.userservice;

import com.soma.spenzy.model.DTO.UserDTO;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Set<UserDTO> getAllUsers() {
        return userMapper.toUserDTOs(new HashSet<>(userRepository.findAll()));
    }

    @Override
    public UserDTO getUserByEmail(String token) {
        SpenzyUser spenzyUser = userRepository
                .findByEmail(jwtService.extractUsername(token.split(" ")[1]))
                .orElseThrow(() -> new IllegalArgumentException(
                        "ERROR: No user with this email.."
                ));

        return userMapper.toUserDTO(spenzyUser);
    }

    @Override
    public UserDTO addNewUser(UserDTO newUserDTO) {
        SpenzyUser newUser = userMapper.toSpenzyUser(newUserDTO);
        userRepository.save(newUser);

        return newUserDTO;
    }

    @Override
    public UserDTO updateUser(String token, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(String token) {
        userRepository.deleteByEmail(jwtService.extractUsername(token.split(" ")[1]));
    }
}
