package com.lastovskiy.app.exception;

public class NoSuchStockInPortfolioException extends TransactionException {
    public NoSuchStockInPortfolioException(String message, int eventId) {
        super(message, eventId);
    }
}
