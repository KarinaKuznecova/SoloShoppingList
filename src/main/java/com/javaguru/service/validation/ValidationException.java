package com.javaguru.service.validation;

public class ValidationException extends RuntimeException {

    ValidationException(String message) {
        super(message);
    }
}
