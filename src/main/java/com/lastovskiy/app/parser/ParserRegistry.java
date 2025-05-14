package com.lastovskiy.app.parser;

import java.util.Map;

public class ParserRegistry {
    private static final Map<String, ArgsParser> registry = Map.of(
            "TRA", new TraParser(),
            "DIV", new DivParser(),
            "CASH", new CashParser()
    );

    public static ParsedLine parse(String line) {
        var colonIndex = line.indexOf(":");
        var key = line.substring(0, colonIndex);
        ArgsParser parser = registry.get(key);
        return new ParsedLine(key, parser.getArgsFromLine(line), line);
    }

}
