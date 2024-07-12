package com.project.demo.users.services;

import com.project.demo.users.exceptions.UserHasNoReadPrivilegeException;
import com.project.demo.users.exceptions.UserNotFoundException;
import com.project.demo.users.models.User;
import com.project.demo.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LoggedUserService loggedUserService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackOn = Exception.class)
    public boolean passwordMatches(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }
    @Transactional(rollbackOn = Exception.class)
    public Optional<User> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    @Transactional(rollbackOn = Exception.class)
    public void checkIfUserExistsOrThrow(String email) {
        findByEmail(email);
    }

    public List<User> getAllUsers(){

        User loggedUser = this.loggedUserService.getLoggedUser();
        if(!loggedUser.getRoles().get(0).getRole().equals("ROLE_ADMIN")){
            throw new UserHasNoReadPrivilegeException();
        }
        return this.userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
