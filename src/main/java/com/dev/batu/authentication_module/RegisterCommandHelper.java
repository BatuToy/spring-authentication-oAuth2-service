package com.dev.batu.authentication_module;

import com.dev.batu.authentication_module.domain.DomainService;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.event.user.UserCreatedEvent;
import com.dev.batu.authentication_module.domain.exception.UserDomainException;
import com.dev.batu.authentication_module.dto.register.RegisterCommand;
import com.dev.batu.authentication_module.exception.ContactDomainException;
import com.dev.batu.authentication_module.exception.DuplicationException;
import com.dev.batu.authentication_module.mapper.UserDataMapper;
import com.dev.batu.authentication_module.ports.output.ContactRepository;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterCommandHelper {

    private final UserDataMapper userDataMapper;
    private final DomainService domainService;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Transactional
    public UserCreatedEvent persistUser(RegisterCommand registerCommand){
        User user = userDataMapper.registerCommandToDomainUser(registerCommand);
        checkUser(user.getEmail());
        UserCreatedEvent userCreatedEvent = domainService.initializeUser(user);
        log.info("User id= {} \t Contact id= {}",
                userCreatedEvent.getUser(), userCreatedEvent.getUser().getContact().getId().getValue());
        saveContact(userCreatedEvent.getUser().getContact(), userCreatedEvent.getUser());
        saveUser(userCreatedEvent.getUser());
        log.info("User and contact saved in to the persist store successfully!");
        return userCreatedEvent;
    }

    private void checkUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.error("User with email= {} already exists!", user.get().getEmail());
            throw new DuplicationException("User with email= " + user.get().getEmail() + " already exists!");
        }
    }

    private void saveUser(User user){
        User userResult = userRepository.save(user);
        if(userResult == null){
            log.error("While saving the user in to persist store an error occur!");
            throw new UserDomainException("While saving the user in to persist store an error occur!");
        }
    }

    private void saveContact(Contact contact, User user) {
        Contact contactResult = contactRepository.save(contact, user);
        if(contactResult == null){
            log.error("While saving the contact in the persist store an error occur!");
            throw new ContactDomainException("While saving the contact in the persist store an error occur!");
        }
    }

}
