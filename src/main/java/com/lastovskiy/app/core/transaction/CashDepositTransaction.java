package com.lastovskiy.app.core.transaction;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.validation.TransactionsValidator;
import lombok.Getter;

@Getter
public class CashDepositTransaction implements Transaction {
    private static final TransactionType TRANSACTION_TYPE = TransactionType.CASH_DEPOSIT;
    private final int eventId;
    private final double amount;

    public CashDepositTransaction(int eventId, double amount) {
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
    public void applyTo(Account account)  {
        account.increaseBalance(this.amount);
    }

    @Override
    public void applyValidator(TransactionsValidator validator, Account account) throws TransactionException {
        validator.validate(account,this);
    }

}
