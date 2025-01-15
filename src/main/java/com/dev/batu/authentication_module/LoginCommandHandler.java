package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.config.security.helper.JwtHelper;
import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.exception.NotFountException;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginCommandHandler {

    private final LoginCommandHelper loginCommandHelper;
    private final UserRepository userRepository;
    private final JwtHelper tokenHelper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginCommand loginCommand) {
        User user = findUserByEmail(loginCommand.getEmail());
        checkPassword(loginCommand.getRawPassword(), user.getEncodedPassword());
        loginCommandHelper.persisLoginAttempt(loginCommand);
        final String token = tokenHelper.generateToken(loginCommand.getEmail());
        return new LoginResponse(token,
                "Login processed successfully for user with email = " + loginCommand.getEmail());
    }

    private void checkPassword(String rawPassword, String encodedPassword){
        if(!passwordEncoder.matches(rawPassword, encodedPassword)){
            log.error("Passwords are not matching!");
            throw new BadCredentialsException("Password is not correct!");
        }
    }

    private User findUserByEmail(String email){
         return userRepository.findByEmail(email)
                .orElseThrow( () -> new NotFountException("User not found with the given email!"));
    }


}
