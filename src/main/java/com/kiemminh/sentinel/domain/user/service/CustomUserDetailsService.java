package com.kiemminh.sentinel.domain.user.service;

import com.kiemminh.sentinel.domain.user.entity.UserInfo;
import com.kiemminh.sentinel.domain.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Dependency injection via constructor (current best practice)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user in the database
        UserInfo userInfo = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Map to Spring Security's User object
        return new User(
                userInfo.getUsername(),
                userInfo.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userInfo.getRole()))
        );
    }
}