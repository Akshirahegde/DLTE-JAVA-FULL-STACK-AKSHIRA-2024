package org.example.Exception;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.*;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super(ResourceBundle.getBundle("Information").getString("user.name"));
    }
}
