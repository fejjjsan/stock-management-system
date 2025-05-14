package com.lastovskiy.app;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.core.Portfolio;

import com.lastovskiy.app.core.TransactionManager;
import com.lastovskiy.app.utils.Utils;

import com.lastovskiy.app.validation.FormatValidator;
import com.lastovskiy.app.validation.ArgumentsValidator;
import com.lastovskiy.app.validation.TransactionsValidator;

import java.io.IOException;


public class SystemMain {
    public static void main(String[] args) throws IOException {

        var path = "src/main/resources/";
        var lines = Utils.readLines(path,"stocks1.txt");
        var account = new Account(new Portfolio());

        var formatValidator = new FormatValidator();
        formatValidator.validate(lines);
        if (formatValidator.hasErrors()) {
            formatValidator.printErrors();
            return;
        }

        var parsedLines = Utils.parseLines(lines);
        var argsValidator = new ArgumentsValidator();
        argsValidator.validate(parsedLines);
        if (argsValidator.hasErrors()) {
            argsValidator.printErrors();
            return;
        }

        var transactions = Utils.mapToTransactions(parsedLines);
        var transactionsValidator = new TransactionsValidator();
        var transactionManager = new TransactionManager(account, transactionsValidator);
        transactionManager.processTransactions(transactions);
        account.printAccountSummary();


        if (transactionManager.hasFailedTransactions()) {
            transactionManager.printFailedTransactions();
        }
    }
}