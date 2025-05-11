package com.example.demo.utility.extracting;

import com.example.demo.enums.LogLevel;
import org.springframework.stereotype.Component;

@Component
public class ExtractDataFromFile implements IExtractDataFromFile {

    private String timeStamp = null;
    private String logType = null;
    private Integer ProcessId = null;
    private String message = null;
    private String threadName = null;

    public static void main(String[] args) {
        prova("2025-05-10T11:09:46.046+02:00  DEBUG 7928 --- [whatappClone] [main] c.example.demo.WhatappCloneApplication   : Starting WhatappCloneApplication using Java 17.0.12 with PID 7928 (C:\\Users\\Anton\\IdeaProjects\\WhatsappClone_back\\target\\classes started by Anton in C:\\Users\\Anton\\IdeaProjects\\WhatsappClone_back)\n");
    }


    // test per i metodi da implementare
    public static void prova(String line) {

    }

    // estrarre il timestamp del log
    @Override
    public void extractTimeStamp(String line) {
        setTimeStamp(line.substring(0, line.indexOf(" ")));
    }

    // estrarre il tipo di log dalla riga di log
    @Override
    public void extractLogType(String line) {

        if (LogLevel.checkIfStringContainsLogLevel(line)) {
            setLogType(
                    line.substring(
                            line.indexOf(" "),
                            line.indexOf(LogLevel.returnWhichLevel(line)) + LogLevel.returnWhichLevel(line).length())
            );

        } else {
            setLogType("N/A");
        }

    }

    // estrarre il processId dalla linea di log
    @Override
    public void extractProcessId(String line) {

    }

    @Override
    public void extractMessage(String line) {

    }

    @Override
    public void extractThreadName(String line) {

    }

    @Override
    public void BuildRecordForDb() {

    }


    // getter e setter
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Integer getProcessId() {
        return ProcessId;
    }

    public void setProcessId(Integer processId) {
        ProcessId = processId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
