package com.lastovskiy.app.core;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {

    private final int stockId;

    private int quantity;
    private double averagePrice;
    private double positionSize;

    public Position(int stockId) {
        this.stockId = stockId;
    }

    void increase(int quantityToAdd, double pricePerStock) {
        var totalAmount = quantityToAdd * pricePerStock;
        var newQuantity = this.quantity + quantityToAdd;
        var newPositionSize = this.positionSize + totalAmount;
        var newAveragePrice = newPositionSize / newQuantity;
        setQuantity(newQuantity);
        setPositionSize(newPositionSize);
        setAveragePrice(newAveragePrice);
    }


     double decrease(int quantityToRemove, double pricePerStock) {
        if (quantityToRemove > this.quantity) {
            return -1;
        }

        var newQuantity = this.quantity - quantityToRemove;

        if (newQuantity == 0) {
            var finalAmount = this.quantity * pricePerStock;
            setQuantity(0);
            setPositionSize(0);
            setAveragePrice(0);
            return finalAmount;
        }

        var totalAmount = quantityToRemove * pricePerStock;
        var newPositionSize = this.positionSize - totalAmount;
        setQuantity(newQuantity);
        setPositionSize(newPositionSize);

        return quantityToRemove * pricePerStock;
    }

    double applyDividend(double dividendPerStock) {
        var totalAmountToPay = this.quantity * dividendPerStock;
        var positionSizeWithOutDividends = this.positionSize - totalAmountToPay;
        var newAveragePrice = positionSizeWithOutDividends / this.quantity;
        var newPositionSize = this.quantity * newAveragePrice;
        setPositionSize(newPositionSize);
        setAveragePrice(newAveragePrice);

        return totalAmountToPay;
    }


    @Override
    public String toString() {
        return String.format("<stockId %d> " +
                "[average price: %.2f] " +
                "[quantity: %d] " +
                "[position size: %.2f]",
                stockId, averagePrice, quantity, positionSize);
    }
}
