package com.lastovskiy.app.validation;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class FormatValidator {
    private final List<String> errorsList = new ArrayList<>();
    private final Set<String> validKeys = Set.of("TRA", "CASH", "DIV");


    /**
     * This pattern matches a comma-separated list of integers or decimal numbers,
     * where each number can optionally be negative.
     * Examples of matching strings:
     *  "1,2,3"
     *  "-1,2.5,-3.75"
     *  "100"
     *  "-42.0,0.001,-7"
     * Examples of non-matching strings:
     *  "1,,2" (double comma)
     *  "1,2," (trailing comma)
     *  "1.2.3" (invalid number)
     */
    private final static Pattern PATTERN = Pattern.compile("(-?\\d+(\\.\\d+)?,)*-?\\d+(\\.\\d+)?");

    public void validate(List<String> lines) {
        for (String line: lines) {
            validateLine(line);
        }
    }

    private void validateLine(String line) {
        int colonIndex = line.indexOf(":");
        if (colonIndex == -1) {
            errorsList.add("Missing < : > symbol in line " + line);
            return;
        }

        var key = line.substring(0, colonIndex);
        if (!validKeys.contains(key)) {
            errorsList.add(("Unknown key: " + key + " in line " + line));
            return;
        }

        var argsPart = line.substring(colonIndex + 1);
        checkPatter(argsPart);
    }

    private void checkPatter(String line) {
        if (!PATTERN.matcher(line).matches()) {
                errorsList.add("Wrong format in line: " + line);
        }
    }

    public boolean hasErrors() {
        return !errorsList.isEmpty();
    }

    public void printErrors() {
        errorsList.forEach(System.out::println);
    }

}
