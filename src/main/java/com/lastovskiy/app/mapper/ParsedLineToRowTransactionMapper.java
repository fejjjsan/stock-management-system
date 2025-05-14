package com.lastovskiy.app.mapper;

import com.lastovskiy.app.parser.ParsedLine;
import com.lastovskiy.app.core.transaction.RowTransaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParsedLineToRowTransactionMapper {
    public static RowTransaction map(ParsedLine line) {
        Map<String, Number> args = line.getArguments();
        return RowTransaction.builder()
                .type(line.getTransactionType())
                .eventId(getInt(args, "eventId"))
                .stockId(getInt(args, "stockId"))
                .quantity(getInt(args, "quantity"))
                .pricePerStock(getDouble(args, "pricePerStock"))
                .dividendPerStock(getDouble(args, "dividendPerStock"))
                .amount(getDouble(args, "amount"))
                .build();
    }

    private static Integer getInt(Map<String, Number> args, String key) {
        return args.containsKey(key) ? args.get(key).intValue() : null;
    }

    private static Double getDouble(Map<String, Number> args, String key) {
        return args.containsKey(key) ? args.get(key).doubleValue() : null;
    }
}
