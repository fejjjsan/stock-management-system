package com.lastovskiy.app.exception;

public class DividendNotApplicableException extends TransactionException {
    public DividendNotApplicableException(String message, int eventId) {
        super(message, eventId);
    }
}
