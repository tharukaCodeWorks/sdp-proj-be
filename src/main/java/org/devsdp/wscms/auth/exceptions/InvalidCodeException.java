package org.devsdp.wscms.auth.exceptions;

public class InvalidCodeException extends RuntimeException{
    public InvalidCodeException(String exception){
        super(exception);
    }
}
