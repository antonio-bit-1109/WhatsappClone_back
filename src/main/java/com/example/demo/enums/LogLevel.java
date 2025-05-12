package com.example.demo.enums;

import java.util.concurrent.atomic.AtomicBoolean;

public enum LogLevel {

    TRACE("TRACE"),
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL");

    private final String logLevel;
    private static String logToReturn = null;


    private LogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public static boolean checkIfStringContainsLogLevel(String line) {
        AtomicBoolean atLeastOne = new AtomicBoolean(false);

        if (line.contains(TRACE.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = TRACE.getLogLevel();
        }

        if (line.contains(DEBUG.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = DEBUG.getLogLevel();
        }

        if (line.contains(INFO.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = INFO.getLogLevel();
        }

        if (line.contains(WARN.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = WARN.getLogLevel();
        }

        if (line.contains(ERROR.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = ERROR.getLogLevel();
        }

        if (line.contains(FATAL.getLogLevel())) {
            atLeastOne.set(true);
            logToReturn = FATAL.getLogLevel();
        }

        return atLeastOne.get();
    }

    public synchronized static String returnWhichLevel(String line) {
        return logToReturn;
    }

//    public static void reset() {
//        logToReturn = null;
//    }
}
