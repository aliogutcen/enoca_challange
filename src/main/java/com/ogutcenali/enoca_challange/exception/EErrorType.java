package com.ogutcenali.enoca_challange.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201,"Invalid Parameter Input",BAD_REQUEST),



    INTERNAL_ERROR(3000,"Unexpected error on the server",INTERNAL_SERVER_ERROR),

    CUSTOMER_NOT_FOUND(2301,"No customer with the id you are looking for was found",BAD_REQUEST),
    ORDER_NOT_FOUND(2302,"No order with the id you are looking for was found",BAD_REQUEST)
    ;

    private int code;

    private String message;

    private HttpStatus httpStatus;
}
