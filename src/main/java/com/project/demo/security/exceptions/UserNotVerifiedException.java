package com.project.demo.security.exceptions;

public class UserNotVerifiedException extends RuntimeException {
    public UserNotVerifiedException(String email){super(email);}
}
