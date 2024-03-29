package com.fsse2305.fsse2305_project_backend.exception.product;

import com.fsse2305.fsse2305_project_backend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){};
    public ProductNotFoundException(String msg){
        super(msg);
    }
}
