package com.lastovskiy.app.core.transaction;


import com.lastovskiy.app.core.Account;
import com.lastovskiy.app.exception.TransactionException;
import com.lastovskiy.app.validation.TransactionsValidator;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SellStockTransaction implements Transaction {
    private static final TransactionType TRANSACTION_TYPE = TransactionType.SELL_STOCK;
    private final int eventId;
    private final int stockId;
    private final int quantity;
    private final double pricePerStock;


    public SellStockTransaction(int eventId, int stockId, int quantity, double pricePerStock) {
        this.eventId = eventId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
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
        var absQuantity = Math.abs(quantity);
        var amount = account.getPortfolio().sellStock(stockId, absQuantity, pricePerStock);
        account.increaseBalance(amount);
    }


    @Override
    public void applyValidator(TransactionsValidator validator, Account account) throws TransactionException {
        validator.validate(account,this);
    }
}
