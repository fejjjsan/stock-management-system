package com.lastovskiy.app.core.transaction;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RowTransaction {
    private final String type;
    private final Double amount;
    private final Integer eventId;
    private final Integer stockId;
    private final Integer quantity;
    private final Double pricePerStock;
    private final Double dividendPerStock;
}
