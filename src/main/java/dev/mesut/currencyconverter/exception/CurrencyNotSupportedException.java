package dev.mesut.currencyconverter.exception;

public class CurrencyNotSupportedException extends RuntimeException{

    private String message;

    public CurrencyNotSupportedException(String message) {
        super(message);
    }

}
