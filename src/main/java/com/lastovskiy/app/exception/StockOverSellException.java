package com.lastovskiy.app.exception;

public class StockOverSellException extends TransactionException {
    public StockOverSellException(String message, int eventId) {
        super(message, eventId);
    }
}


