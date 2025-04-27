package com.lastovskiy.app.parser;

import java.util.Map;

public class CashParser implements ArgsParser {
    private static final Integer MIN_ARGS_LENGTH = 1;
    private static final Integer MAX_ARGS_LENGTH = 2;

    @Override
    public Map<String, Number> getArgsFromLine(String line) {
        var args = line.split(":")[1].split(",");
        var length = args.length;
        if (length < MIN_ARGS_LENGTH || length > MAX_ARGS_LENGTH) {
            throw new IllegalArgumentException("CASH expects 1 or 2 arguments: " + line);
        }
        var offset = length == MAX_ARGS_LENGTH ? 1 : 0;

        var eventId = offset == 1 ? Integer.parseInt(args[0]) : 0;
        var amount = Double.parseDouble(args[offset]);

        return Map.of("eventId", eventId,"amount", amount);
    }
}
