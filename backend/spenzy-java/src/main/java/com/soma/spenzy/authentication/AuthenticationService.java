package com.soma.spenzy.authentication;

import com.soma.spenzy.model.DTO.UserCredentialsDTO;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.SpenzyUserRole;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.AppUserDetailsService;
import com.soma.spenzy.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;

    public UserCredentialsDTO register(UserCredentialsDTO userCredentialsDTO) {
        SpenzyUser newUser = SpenzyUser.builder()
                .name(userCredentialsDTO.name())
                .email(userCredentialsDTO.email())
                .password(passwordEncoder.encode(userCredentialsDTO.password()))
                .userRole(SpenzyUserRole.ADMIN)
                .build();

        userRepository.save(newUser);

        return userCredentialsDTO;
    }

    public AuthenticationResponse authenticate(UserCredentialsDTO userCredentialsDTO) {

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                userCredentialsDTO.email(),
                userCredentialsDTO.password());

        authenticationManager.authenticate(upat);

        UserDetails companyUser = appUserDetailsService.loadUserByUsername(userCredentialsDTO.email());

        String jwtToken = jwtService.generateToken(companyUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
