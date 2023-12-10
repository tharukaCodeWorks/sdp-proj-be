package org.devsdp.wscms.auth.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String exception){
        super(exception);
    }
}
