package com.soma.spenzy.security;

import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SpenzyUser> appUser = userRepository.findByEmail(email);

        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        SpenzyUser user = appUser.get();

        // roles
        // List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new User(user.getEmail(), user.getPassword(), List.of());
    }
}
