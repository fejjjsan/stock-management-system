package com.lastovskiy.app.parser;

import java.util.Map;

public class DivParser implements ArgsParser {
    private static final int MIN_ARGS_LENGTH = 2;
    private static final int MAX_ARGS_LENGTH = 3;


    @Override
    public Map<String, Number> getArgsFromLine(String line) {
        var args = line.split(":")[1].split(",");
        var length = args.length;
        if (length < MIN_ARGS_LENGTH || length > MAX_ARGS_LENGTH) {
            throw new IllegalArgumentException("DIV expects 2 or 3 arguments: " + line);
        }

        var offset = (length == MAX_ARGS_LENGTH) ? 1 : 0;

        var eventId = (offset == 1) ? Integer.parseInt(args[0]) : 0;
        var stockId = Integer.parseInt(args[offset]);
        var dividendPerStock = Double.parseDouble(args[1 + offset]);

        return Map.of("eventId", eventId, "stockId", stockId, "dividendPerStock", dividendPerStock);
    }
}
