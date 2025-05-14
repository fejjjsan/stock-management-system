package com.lastovskiy.app.exception;

public class TransactionException extends Exception {
    private final int eventId;

    public TransactionException(String message, int eventId) {
        super("Transaction with ID <" + eventId + "> failed: " + message);
        this.eventId = eventId;
    }

    public int getEventId() {
        return this.eventId;
    }
}
