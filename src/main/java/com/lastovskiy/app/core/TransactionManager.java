package com.lastovskiy.app.core;

import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.core.transaction.Transaction;
import com.lastovskiy.app.validation.TransactionsValidator;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionManager {
    private final List<String> failedTransaction = new ArrayList<>();
    private final Set<Integer> seenTransactions = new TreeSet<>();
    private final Account account;
    private final TransactionsValidator transactionsValidator;
    public void processTransactions(List<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            var id = transaction.getTransactionId();
            try {
                if (seenTransactions.contains(id)) {
                    continue;
                }
                transaction.applyValidator(transactionsValidator, account);
                transaction.applyTo(account);
                seenTransactions.add(id);
            } catch (TransactionException e) {
                failedTransaction.add(e.getMessage());
            }
        }
    }

    public boolean hasFailedTransactions() {
        return !failedTransaction.isEmpty();
    }

    public void printFailedTransactions() {
        java.lang.System.out.println("Failed transactions:");
        failedTransaction.forEach(java.lang.System.out::println);
    }
}
