package com.lastovskiy.app.core.transaction;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.validation.TransactionsValidator;
import lombok.Getter;

@Getter
public class DividendTransaction implements Transaction {
    private static final TransactionType TRANSACTION_TYPE = TransactionType.DIVIDEND;
    private final int eventId;
    private final int stockId;
    private final double dividendPerStock;

    public DividendTransaction(int eventId, int stockId, double dividendPerStock) {
        this.eventId = eventId;
        this.stockId = stockId;
        this.dividendPerStock = dividendPerStock;
    }

    @Override
    public TransactionType getType() {
        return TRANSACTION_TYPE;
    }

    @Override
    public int getTransactionId() {
        return eventId;
    }

    @Override
    public void applyTo(Account account) {
        var totalDividends = account.getPortfolio().applyDividends(stockId, dividendPerStock);
        account.increaseBalance(totalDividends);
    }

    @Override
    public void applyValidator(TransactionsValidator validator, Account account) throws TransactionException {
        validator.validate(account,this);
    }

}
