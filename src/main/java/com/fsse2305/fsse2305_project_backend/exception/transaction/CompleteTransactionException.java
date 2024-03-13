package com.fsse2305.fsse2305_project_backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompleteTransactionException extends RuntimeException{
    public CompleteTransactionException(){};
    public CompleteTransactionException(String msg){super(msg);}
}
