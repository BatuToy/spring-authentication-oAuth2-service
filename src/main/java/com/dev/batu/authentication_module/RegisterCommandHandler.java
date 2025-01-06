package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.domain.user.User;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.dto.register.RegisterResponse;
import com.dev.batu.authentication_module.exception.DomainException;
import com.dev.batu.authentication_module.exception.DuplicationException;
import com.dev.batu.authentication_module.mapper.AuthDataMapper;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RegisterCommandHandler {

    private final UserRepository userRepository;
    private final AuthDataMapper authDataMapper;

    @Transactional
    public RegisterResponse register(RegisterCommand registerCommand) {
        checkUser(registerCommand.getEmail());
        User user = authDataMapper.registerCommandToDomainUser(registerCommand);
        saveUser(user);
        // Todo: Roles need to be initialized!
        return authDataMapper.domainUserToRegisterResponse(user,
                "User registered with user id= " + user.getId().getValue());
    }

    private void checkUser(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            log.error("User with email= {} already exists!", user.get().getEmail());
            throw new DuplicationException("User with email= " + user.get().getEmail() + " already exists!");
        }
    }

    private void saveUser(User user){
        User userResult = userRepository.save(user);
        if(userResult == null){
            log.error("While saving the user in to persist store an error occur!");
            throw new DomainException("While saving the user in to persist store an error occur!");
        }
    }

}
