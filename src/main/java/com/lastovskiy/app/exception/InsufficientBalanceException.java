package com.lastovskiy.app.exception;

public class InsufficientBalanceException extends TransactionException {
    public InsufficientBalanceException(String message, int eventId) {
        super(message, eventId);
    }
}
