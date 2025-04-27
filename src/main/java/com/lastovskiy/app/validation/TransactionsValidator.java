package com.lastovskiy.app.validation;

import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.DividendNotApplicableException;
import com.lastovskiy.app.exception.InsufficientBalanceException;
import com.lastovskiy.app.exception.NoSuchStockInPortfolioException;
import com.lastovskiy.app.exception.StockOverSellException;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.core.transaction.Transaction;
import com.lastovskiy.app.core.transaction.BuyStockTransaction;
import com.lastovskiy.app.core.transaction.SellStockTransaction;
import com.lastovskiy.app.core.transaction.DividendTransaction;
import com.lastovskiy.app.core.transaction.CashWithdrawlTransaction;


public class TransactionsValidator {
    public void validate(Account account, Transaction transaction) throws TransactionException {

        switch (transaction.getType()) {
            case BUY_STOCK -> validateBuyTransaction(account, transaction);
            case SELL_STOCK -> validateSellTransaction(account, transaction);
            case DIVIDEND -> validateDividendTransaction(account, transaction);
            case CASH_WITHDRAWAL-> validateCashWithdrawalTransaction(account, transaction);
            case CASH_DEPOSIT -> {}
        }

    }

    private void validateBuyTransaction(Account account, Transaction transaction) throws TransactionException {
        var actualTransaction = (BuyStockTransaction) transaction;
        var totalAmount = actualTransaction.getTotalAmount();
        var eventId = actualTransaction.getEventId();
        if (account.hasEnoughBalance(totalAmount)) {
            throw new InsufficientBalanceException("Insufficient balance to buy a stock: account balance -> " + account.getBalance()
                    + " transaction amount -> " + totalAmount, eventId);
        }
    }


    private void validateSellTransaction(Account account, Transaction transaction) throws TransactionException  {
        var portfolio = account.getPortfolio();
        var actualTransaction = (SellStockTransaction) transaction;
        var stockId = actualTransaction.getStockId();
        var eventId = actualTransaction.getEventId();
        var quantity = actualTransaction.getQuantity();
        if (!portfolio.isStockInHoldings(stockId)) {
            throw new NoSuchStockInPortfolioException("Attempt to sell stock that is not in portfolio", eventId);
        } else if (!portfolio.isPositionDecreasable(stockId, quantity)) {
            throw new StockOverSellException("Attempt to sell more stocks then in portfolio", eventId);
        }
    }


    private void validateDividendTransaction(Account account, Transaction transaction) throws TransactionException {
        var actualTransaction = (DividendTransaction) transaction;
        var stockId = actualTransaction.getStockId();
        var eventId = actualTransaction.getEventId();
        if (!account.getPortfolio().isStockInHoldings(stockId)) {
            throw new DividendNotApplicableException("Attempt to receive dividends on a stock that is not in portfolio",
                    eventId);
        }
    }


    private void validateCashWithdrawalTransaction(Account account, Transaction transaction) throws TransactionException {
        var actualTransaction = (CashWithdrawlTransaction) transaction;
        var amount = actualTransaction.getAmount();
        var eventId = actualTransaction.getEventId();
        if (account.hasEnoughBalance(amount)) {
            throw new InsufficientBalanceException("Insufficient balance: " + account.getBalance()
                    + " transaction amount: " + amount, eventId);
        }
    }

}
