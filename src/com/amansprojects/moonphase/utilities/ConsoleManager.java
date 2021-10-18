package com.amansprojects.moonphase.utilities;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class ConsoleManager implements Filter {
    private final Main plugin;

    public ConsoleManager(Main main) { this.plugin = main; }

    @Override
    public boolean isLoggable(LogRecord record) {
        for (String string : this.plugin.getConfig().getStringList("hidden console messages")) {
            if (record.getMessage().contains(string)) { return false; }
        }
        return true;
    }
}