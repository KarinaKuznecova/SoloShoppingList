package com.javaguru.SoloShoppingList.service.validation;

public class ValidationException extends RuntimeException {

    ValidationException(String message) {
        super(message);
    }
}
