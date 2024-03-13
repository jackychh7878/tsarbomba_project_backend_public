package com.fsse2305.fsse2305_project_backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdateTransactionDetailsException extends RuntimeException{
    public UpdateTransactionDetailsException(){};
    public UpdateTransactionDetailsException(String msg){super(msg);}
}
