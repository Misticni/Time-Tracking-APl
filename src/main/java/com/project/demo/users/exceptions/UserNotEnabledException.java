package com.project.demo.users.exceptions;

public class UserNotEnabledException extends RuntimeException{
    public UserNotEnabledException(String message){
        super(message);
    }
}
