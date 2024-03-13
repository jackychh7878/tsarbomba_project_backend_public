package com.fsse2305.fsse2305_project_backend.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateProductException extends RuntimeException{
    public CreateProductException(){};
    public CreateProductException (String msg){
        super(msg);
    }
}
