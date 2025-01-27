package com.dev.batu.authentication_module.security.userdetails;

import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(
                        user -> org.springframework.security.core.userdetails.User.builder()
                                .username(user.getEmail())
                                .password(user.getEncodedPassword())
                                .authorities(user.getRoles().stream()
                                        .map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList())
                                .build())
                                .orElseThrow(() -> new UsernameNotFoundException("Username not found with user name= " + email)
                );
    }
}
