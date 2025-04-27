package com.lastovskiy.app.validation;

import com.lastovskiy.app.parser.ParsedLine;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class ArgumentsValidator {
    private final List<String> validationErrors = new ArrayList<>();
    private final Set<String> validKeys = Set.of("TRA", "CASH", "DIV");

    public void validate(List<ParsedLine> lines) {
        for (ParsedLine line: lines) {
            validateArgs(line);
        }
    }

    private void validateArgs(ParsedLine line) {
        var args = line.getArguments();
        for (String key: args.keySet()){
            if (key.equals("quantity") || key.equals("amount")) {
                continue;
            }
            if (isNegative(args.get(key))) {
                validationErrors.add(key + " could not be negative in line: " + line.getLine());
            }
        }
    }

    private boolean isNegative(Number number) {
        return number.doubleValue() < 0;
    }

    public boolean hasErrors() {
        return !validationErrors.isEmpty();
    }

    public void printErrors() {
        validationErrors.forEach(System.out::println);
    }

}
