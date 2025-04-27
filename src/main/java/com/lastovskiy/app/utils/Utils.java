package com.lastovskiy.app.utils;

import com.lastovskiy.app.parser.ParsedLine;
import com.lastovskiy.app.parser.ParserRegistry;
import com.lastovskiy.app.mapper.ParsedLineToRowTransactionMapper;
import com.lastovskiy.app.core.transaction.Transaction;
import com.lastovskiy.app.core.transaction.TransactionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> readLines(String path, String file) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(path + file));
        List<String> lines = new ArrayList<>();
        while(br.ready()) {
            var line = br.readLine();
            lines.add(line);
        }
        return lines;
    }

    public static List<ParsedLine> parseLines(List<String> lines) {
        return lines.stream()
                .map(ParserRegistry::parse)
                .toList();
    }

    public static List<Transaction> mapToTransactions(List<ParsedLine> parsedLines) {
        return parsedLines.stream()
                .map(ParsedLineToRowTransactionMapper::map)
                .map(TransactionFactory::of)
                .toList();
    }
}
