package com.project.demo.users.controllers;

import com.project.demo.users.converters.UserConverter;
import com.project.demo.users.models.User;
import com.project.demo.users.models.UserDTO;
import com.project.demo.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return this.userConverter.toUserDTOList(this.userService.getAllUsers());
    }
}
