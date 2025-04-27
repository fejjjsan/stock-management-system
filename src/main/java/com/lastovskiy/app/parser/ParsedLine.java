package com.lastovskiy.app.parser;

import lombok.Getter;

import java.util.Map;

@Getter
public class ParsedLine {
    public final String transactionType;
    public final Map<String, Number> arguments;
    public final String line;

    public ParsedLine(String transactionType, Map<String, Number> arguments, String line) {
        this.transactionType = transactionType;
        this.arguments = arguments;
        this.line = line;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public Map<String, Number> getArguments() {
        return arguments;
    }
}
