package com.project.demo.users.converters;

import com.project.demo.users.models.User;
import com.project.demo.users.models.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getPerson() != null ?  user.getPerson().getFirstName() : null,
                user.getPerson() != null ?  user.getPerson().getLastName() : null,
                user.getEmail()
        );
    }
    public List<UserDTO> toUserDTOList(List<User> users){
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }
}
