package com.dev.batu.authentication_module.mapper;

import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.dto.login.LoginAttemptResult;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserDataMapper {

    private final PasswordEncoder passwordEncoder;

    public User registerCommandToDomainUser(RegisterCommand registerCommand){
        return User.builder()
                .email(registerCommand.getEmail())
                .userName(registerCommand.getUserName())
                .encodedPassword(passwordEncoder.encode(registerCommand.getRawPassword()))
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .updatedAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .contact(contactDtoToContact(registerCommand.getContact()))
                .build();
    }

    public RegisterResponse domainUserToRegisterResponse(User user, String message){
        return RegisterResponse.builder()
                .userName(user.getUserName())
                .message(message)
                .build();
    }


    public List<LoginAttemptResult> loginAttemptsToLoginAttemptResults(List<LoginAttempt> loginAttempts){
        return loginAttempts.stream()
                .map((loginAttempt -> LoginAttemptResult.builder()
                        .attemptAt(loginAttempt.getAttemptedAt())
                        .isSuccess(loginAttempt.isSuccess())
                        .build()))
                .toList();
    }

    private Contact contactDtoToContact(com.dev.batu.authentication_module.dto.register.Contact contact){
        return new Contact(contact.getPhoneNumber());
    }
}
