package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.config.security.helper.JwtHelper;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.loginAttempt.UserAuthenticateEvent;
import com.dev.batu.authentication_module.dto.login.LoginCommand;
import com.dev.batu.authentication_module.dto.login.LoginResponse;
import com.dev.batu.authentication_module.exception.NotFountException;
import com.dev.batu.authentication_module.ports.input.message.KafkaUserLogInMessagePublisher;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginCommandHandler {

    private final LoginCommandHelper loginCommandHelper;
    private final KafkaUserLogInMessagePublisher kafkaUserLogInMessagePublisher;
    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginCommand loginCommand) {
        UserDetails user = findUserByEmail(loginCommand.getEmail());
        checkPassword(loginCommand.getRawPassword(), user.getPassword());
        UserAuthenticateEvent userAuthenticateEvent = loginCommandHelper.persisLoginAttempt(loginCommand);
        kafkaUserLogInMessagePublisher.send(userAuthenticateEvent);
        return new LoginResponse(
                jwtHelper.generateToken(user),
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
