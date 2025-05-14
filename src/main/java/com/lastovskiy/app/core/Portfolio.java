package com.lastovskiy.app.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<Integer, Position> holdings = new HashMap<>();

    public Map<Integer, Position> getHoldings() {
        return Collections.unmodifiableMap(holdings);
    }

    public void buyStock(int stockId, int quantityToAdd, double pricePerStock) {
        if (!isStockInHoldings(stockId)){
            addNewPosition(stockId);
        }
        var position = holdings.get(stockId);
        position.increase(quantityToAdd, pricePerStock);
    }

    public double sellStock(int stockId, int quantityToRemove, double pricePerStock) {
        double result = 0;
        if (isStockInHoldings(stockId)) {
            var position = holdings.get(stockId);
            result = position.decrease(quantityToRemove, pricePerStock);
            if (position.getQuantity() == 0) {
                removePosition(stockId);
            }
        }
        return result;
    }

    public double applyDividends(int stockId, double dividendPerStock) {
        var position = holdings.get(stockId);
        return position.applyDividend(dividendPerStock);
    }

    public boolean isStockInHoldings(int stockId) {
        return holdings.containsKey(stockId);
    }
    public boolean isPositionDecreasable(int stockId, int quantity) {
        var position = holdings.get(stockId);
        var currentQuantity = position.getQuantity();
        return currentQuantity >= quantity;
    }

    private void addNewPosition(int stockId) {
        holdings.put(stockId, new Position(stockId));
    }

    private void removePosition(int stockId) {
        holdings.remove(stockId, holdings.get(stockId));
    }

}
