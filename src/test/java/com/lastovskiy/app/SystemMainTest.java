package com.lastovskiy.app;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.core.Portfolio;
import com.lastovskiy.app.core.TransactionManager;
import com.lastovskiy.app.core.transaction.BuyStockTransaction;
import com.lastovskiy.app.core.transaction.CashDepositTransaction;
import com.lastovskiy.app.core.transaction.DividendTransaction;
import com.lastovskiy.app.core.transaction.SellStockTransaction;
import com.lastovskiy.app.core.transaction.Transaction;

import com.lastovskiy.app.validation.TransactionsValidator;
import org.junit.jupiter.api.Test;

import java.util.List;


public class SystemMainTest {

    @Test
    public void testProcessTransactions() {
        var account = new Account(new Portfolio());
        var transactionValidator = new TransactionsValidator();

        List<Transaction> transactions = List.of(
                new CashDepositTransaction(999, 10000),
                new BuyStockTransaction(1000, 3, 10, 250),
                new BuyStockTransaction(1001, 3, 15, 245),
                new DividendTransaction(1003, 3, 5),
                new SellStockTransaction(1002, 3, -8, 260),
                new BuyStockTransaction(1004,4,50,25)
        );

        var transactionManager = new TransactionManager(account, transactionValidator);
        transactionManager.processTransactions(transactions);
    }
}
