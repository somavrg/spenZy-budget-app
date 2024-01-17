package com.soma.spenzy.authentication;

import com.soma.spenzy.model.DTO.UserCredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserCredentialsDTO register(@RequestBody UserCredentialsDTO registerUserDTO) {
        return authenticationService.register(registerUserDTO);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody UserCredentialsDTO registerUserDTO) {
        return authenticationService.authenticate(registerUserDTO);
    }
}
