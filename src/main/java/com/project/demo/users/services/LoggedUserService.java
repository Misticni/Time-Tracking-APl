package com.project.demo.users.services;

import com.project.demo.users.exceptions.UserNotFoundException;
import com.project.demo.users.models.User;
import com.project.demo.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoggedUserService {
    private final UserRepository userRepository;

    private Optional<User> getLoggedUserOptional(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null){
            return Optional.empty();
        }

        String email = authentication.getPrincipal().toString();
        return userRepository.findByEmail(email);
    }

    public User getLoggedUser(){
        return getLoggedUserOptional().orElseThrow(UserNotFoundException::new);
    }
}
