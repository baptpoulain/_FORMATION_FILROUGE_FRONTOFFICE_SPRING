package com.example.FilRougeFrontOffice.exception;

public class UserAlreadyExistException extends Throwable {

    public UserAlreadyExistException(String username){
        super(username + " already exist in database");
    }
}
