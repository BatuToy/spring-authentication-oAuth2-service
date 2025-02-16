package com.dev.batu.authentication_module.security.userdetails;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.dataaccess.user.repo.UserJpaRepository;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
