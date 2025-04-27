package com.lastovskiy.app.core.transaction;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionFactory {
    public static Transaction of(RowTransaction row) {
        var type = row.getType();

        return switch (type) {
            case "TRA" -> createTradeTransaction(row);
            case "DIV" -> createDividendTransaction(row);
            case "CASH" -> createCashTransaction(row);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }


    private static Transaction createTradeTransaction(RowTransaction row) {
        var eventId = row.getEventId();
        var stockId = row.getStockId();
        var quantity = row.getQuantity();
        var pricePerStock = row.getPricePerStock();

        return row.getQuantity() > 0
                ? new BuyStockTransaction(eventId, stockId, quantity, pricePerStock)
                : new SellStockTransaction(eventId, stockId, quantity, pricePerStock);
    }


    private static Transaction createDividendTransaction(RowTransaction row) {
        var eventId = row.getEventId();
        var stockId = row.getStockId();
        var dividendPerStock = row.getDividendPerStock();

        return new DividendTransaction(eventId, stockId, dividendPerStock);
    }

    private static Transaction createCashTransaction(RowTransaction row) {
        var eventId = row.getEventId();
        var amount = row.getAmount();

        return row.getAmount() > 0
                ? new CashDepositTransaction(eventId, amount)
                : new CashWithdrawlTransaction(eventId, amount);
    }
}
