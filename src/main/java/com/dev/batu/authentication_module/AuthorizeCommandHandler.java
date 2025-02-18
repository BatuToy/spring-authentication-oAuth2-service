package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.DomainService;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.event.user.UserAuthorizedEvent;
import com.dev.batu.authentication_module.domain.exception.UserDomainException;
import com.dev.batu.authentication_module.dto.authorize.AuthorizeResponse;
import com.dev.batu.authentication_module.dto.authorize.AuthorizeUserCommand;
import com.dev.batu.authentication_module.exception.NotFountException;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizeCommandHandler {

    private final UserRepository userRepository;
    private final DomainService domainService;

    @Transactional
    public AuthorizeResponse authorizeUser(AuthorizeUserCommand authorizeUserCommand){
        UserAuthorizedEvent userAuthorizedEvent = domainService.authorizeUser(findByEmail(authorizeUserCommand.getEmail()));
        User user = userAuthorizedEvent.getUser();
        saveUser(user);
        return new AuthorizeResponse(
                user.getId().getValue(),
                user.getEmail(),
                user.getRoles().stream().toList().toString()
        );
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFountException("User not found with email= " + email)
        );
    }

    private void saveUser(User user){
        User result  = userRepository.save(user);
        if(result == null){
            log.error("User could not be saved with id={}", user.getId().getValue());
            throw new UserDomainException("User could not be saved with id= " + user.getId().getValue());
        }
    }

    // maybe save the roles too?


}
