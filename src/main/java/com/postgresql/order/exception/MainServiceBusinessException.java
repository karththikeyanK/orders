package com.postgresql.order.exception;

public class MainServiceBusinessException extends RuntimeException {

        public MainServiceBusinessException(String message) {
            super(message);
        }
}
