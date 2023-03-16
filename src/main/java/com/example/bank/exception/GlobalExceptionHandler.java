package com.example.bank.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity cardNotFoundException(CardNotFoundException cardNotFoundException){
        return new ResponseEntity<>("Card Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity orderNotFoundException(OrderNotFoundException  orderNotFoundException){
        return new ResponseEntity<>("Order Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = CardCodeException.class)
    public ResponseEntity cardCodeException(CardCodeException  cardCodeException){
        return new ResponseEntity<>("Card Code Failed", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CvvException.class)
    public ResponseEntity cvvException(CvvException  cvvException){
        return new ResponseEntity<>("CVV Failed", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CardYearException.class)
    public ResponseEntity cardYearException(CardYearException  cardYearException){
        return new ResponseEntity<>("Card Year Failed", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = RoleNotFoundExceptions.class)
    public ResponseEntity roleNotFoundException(RoleNotFoundExceptions  roleNotFoundExceptions){
        return new ResponseEntity<>("Role Not Found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = BalanceAmountIsLowException.class)
    public ResponseEntity balanceAmountIsLowException(BalanceAmountIsLowException balanceAmountIsLowException){
        return new ResponseEntity<>("Balance Amount Is Low", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity productNotFoundException(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ProductStockLawIsOrderProductNumberException.class)
    public ResponseEntity productStockLawIsOrderProductNumberException(ProductStockLawIsOrderProductNumberException productStockLawIsOrderProductNumberException){
        return new ResponseEntity<>("Product Stock Law Is Than OrderProductNumber ", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ShoppingCartNotFoundException.class)
    public ResponseEntity shoppingCartNotFoundException(ShoppingCartNotFoundException shoppingCartNotFoundException){
        return new ResponseEntity<>("ShoppingCart Not Found  ", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ShoppingCartEmptyException.class)
    public ResponseEntity shoppingCartEmptyException(ShoppingCartEmptyException shoppingCartEmptyException){
        return new ResponseEntity<>("ShoppingCart Is Empty  ", HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> details = new LinkedHashMap<>();
        details.put("Timestamp", new Date());
        details.put("Status", status.value());

        List<String> error = ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        details.put("Error : ",error);
        return new ResponseEntity(details, headers,status);
    }

}
