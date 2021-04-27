package com.hotmart.marketplace.exception;

public class MarketPlaceException extends RuntimeException{

    public MarketPlaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarketPlaceException(String message) {
        super(message);
    }
}
