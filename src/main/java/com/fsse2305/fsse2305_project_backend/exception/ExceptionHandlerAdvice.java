package com.fsse2305.fsse2305_project_backend.exception;

import com.fsse2305.fsse2305_project_backend.exception.cartItem.AddItemToCartException;
import com.fsse2305.fsse2305_project_backend.exception.cartItem.DeleteCartItemException;
import com.fsse2305.fsse2305_project_backend.exception.cartItem.UpdateCartItemException;
import com.fsse2305.fsse2305_project_backend.exception.product.CreateProductException;
import com.fsse2305.fsse2305_project_backend.exception.product.NoStockException;
import com.fsse2305.fsse2305_project_backend.exception.product.ProductNotFoundException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.CompleteTransactionException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.GetTransactionDetailsException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.GetTransactionProductDetailsException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.UpdateTransactionDetailsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ErrorMessage handleCreateProductException (CreateProductException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleProductNotFoundException (ProductNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleAddItemToCartException (AddItemToCartException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleUpdateCartItemException (UpdateCartItemException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleDeleteCartItemException (DeleteCartItemException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleGetTransactionDetailsException (GetTransactionDetailsException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }
    @ExceptionHandler
    public ErrorMessage handleGetTransactionProductDetailsException (GetTransactionProductDetailsException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleUpdateTransactionDetailsException (UpdateTransactionDetailsException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleCompleteTransactionException (CompleteTransactionException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler
    public ErrorMessage handleNoStockException (NoStockException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }


}
