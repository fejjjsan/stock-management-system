package com.lastovskiy.app.core;

import lombok.Getter;

@Getter
public class Account {
    private double balance;
    private final Portfolio portfolio;

    public Account(Portfolio portfolio) {
        this.balance = 0;
        this.portfolio = portfolio;
    }

    public boolean hasEnoughBalance(double amount) {
        var isAmountNegative = amount < 0;
        if (isAmountNegative) {
            return !(balance >= Math.abs(amount));
        }
        return !(balance >= amount);
    }
    public void decreaseBalance(double amount) {
        this.balance = this.balance - amount;
    }
    public void increaseBalance(double amount) {
        this.balance = this.balance + amount;
    }

    public void printAccountSummary() {
        System.out.println("Current balance: " + this.balance);
        System.out.println("Current positions:");
        for(Integer key : portfolio.getHoldings().keySet()) {
            var position = portfolio.getHoldings().get(key);
            System.out.println(position.toString());
        }
    }
}
