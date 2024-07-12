package com.project.demo.users.models;

public record UserDTO (
        Long id,
        String firstName,
        String lastName,
        String email
){
}
