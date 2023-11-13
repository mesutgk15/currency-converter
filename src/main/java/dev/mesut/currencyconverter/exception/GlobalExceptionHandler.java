package dev.mesut.currencyconverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({CurrencyNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(CurrencyNotSupportedException exception) {
        ExceptionResponse response = prepareResponseObject(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    public ExceptionResponse prepareResponseObject(String message, HttpStatus status) {

        return ExceptionResponse.builder()
                .message(message)
                .status(status)
                .timeStamp(System.currentTimeMillis())
                .build();
    }

}
