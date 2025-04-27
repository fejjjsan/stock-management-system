package com.lastovskiy.app.core.transaction;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.validation.TransactionsValidator;

public interface Transaction {
    TransactionType getType();
    int getTransactionId();
    void applyTo(Account account);
    void applyValidator(TransactionsValidator validator, Account account) throws TransactionException;
}
