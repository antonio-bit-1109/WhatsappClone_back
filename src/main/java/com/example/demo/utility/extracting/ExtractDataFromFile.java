package com.example.demo.utility.extracting;

import org.springframework.stereotype.Component;

@Component
public class ExtractDataFromFile implements IExtractDataFromFile {

    private String timeStamp = null;
    private String logType = null;
    private Integer ProcessId = null;
    private String message = null;
    private String threadName = null;

    @Override
    public void extractTimeStamp(String line) {

    }

    @Override
    public void extractLogType(String line) {

    }

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
