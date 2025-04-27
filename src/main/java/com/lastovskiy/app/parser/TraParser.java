package com.lastovskiy.app.parser;

import java.util.Map;

public class TraParser implements ArgsParser {
    private static final Integer MIN_ARGS_LENGTH = 3;
    private static final Integer MAX_ARGS_LENGTH = 4;

    @Override
    public Map<String, Number> getArgsFromLine(String line) {
        var args = line.split(":")[1].split(",");
        var length = args.length;
        if (length < MIN_ARGS_LENGTH || length > MAX_ARGS_LENGTH) {
            throw new IllegalArgumentException("TRA expects 3 or 4 arguments: " + line);
        }

        var offset = length == MAX_ARGS_LENGTH ? 1 : 0;

        var eventId = (offset == 1) ? Integer.parseInt(args[0]) : 0;
        var stockId = Integer.parseInt(args[offset]);
        var quantity = Integer.parseInt(args[1 + offset]);
        var pricePerStock = Double.parseDouble(args[2 + offset]);

        return Map.of("eventId", eventId, "stockId", stockId, "quantity", quantity, "pricePerStock", pricePerStock);

    }
}
