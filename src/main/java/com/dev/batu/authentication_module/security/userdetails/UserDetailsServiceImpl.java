package com.dev.batu.authentication_module.security.userdetails;

import com.dev.batu.authentication_module.dataaccess.user.repo.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(
                    u -> org.springframework.security.core.userdetails.User.builder()
                            .username(u.getEmail())
                            .password(u.getPassword())
                            .build())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with user name= " + email)
        );
    }
}
