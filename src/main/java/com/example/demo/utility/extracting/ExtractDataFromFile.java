package com.example.demo.utility.extracting;

import com.example.demo.entity.StorageLogs;
import com.example.demo.enums.LogLevel;
import com.example.demo.repository.StorageLogsRepository;
import com.example.demo.utility.factory.Factory;
import org.springframework.stereotype.Component;

@Component
public class ExtractDataFromFile implements IExtractDataFromFile {

    private String timeStamp = null;
    private String logType = null;
    private Integer ProcessId = null;
    private String message = null;
    private String threadName = null;

    private Factory factory;
    private final StorageLogsRepository storageLogsRepository;

    public ExtractDataFromFile(StorageLogsRepository storageLogsRepository) {
        this.storageLogsRepository = storageLogsRepository;
    }

    public void resetValues() {
        setMessage(null);
        setThreadName(null);
        setProcessId(null);
        setLogType(null);
        setTimeStamp(null);
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

        setProcessId(
                Integer.parseInt(
                        line.substring(
                                line.indexOf("---") - 5,
                                line.indexOf("---") - 1
                        )
                )

        );

    }

    @Override
    public void extractThreadName(String line) {
        String port = System.getenv("PORT");

        if (port != null) {
            setThreadName(
                    line.substring(
                            line.indexOf(port) - 10,
                            line.indexOf(port) + 11
                    )

            );
        }

    }

    @Override
    public void extractMessage(String line) {
        setMessage(
                line.substring(
                        line.indexOf(" : ")
                )
        );
    }


    @Override
    public void BuildRecordForDb() {
        StorageLogs entity = this.factory.createEntityStorageLog(
                getTimeStamp(),
                getLogType(),
                getProcessId(),
                getMessage(),
                getThreadName()
        );
        this.resetValues();
        this.storageLogsRepository.save(entity);

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
