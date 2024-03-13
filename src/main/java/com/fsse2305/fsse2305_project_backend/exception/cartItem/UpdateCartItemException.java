package com.fsse2305.fsse2305_project_backend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdateCartItemException extends RuntimeException{
    public UpdateCartItemException(){};
    public UpdateCartItemException(String msg){
        super(msg);
    }
}
