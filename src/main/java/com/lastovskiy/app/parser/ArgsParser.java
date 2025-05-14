package com.lastovskiy.app.parser;

import java.util.Map;

public interface ArgsParser {
    Map<String, Number> getArgsFromLine(String line);
}
