package com.lastovskiy.app.core.transaction;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.validation.TransactionsValidator;
import lombok.Getter;

@Getter
public class CashWithdrawlTransaction implements Transaction {
    private static final TransactionType TRANSACTION_TYPE = TransactionType.CASH_WITHDRAWAL;
    private final int eventId;
    private final double amount;

    public CashWithdrawlTransaction(int eventId, double amount) {
        this.eventId = eventId;
        this.amount = amount;
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
        var absAmount = Math.abs(amount);
        account.decreaseBalance(absAmount);
    }

    @Override
    public void applyValidator(TransactionsValidator validator, Account account) throws TransactionException {
        validator.validate(account,this);
    }

}
