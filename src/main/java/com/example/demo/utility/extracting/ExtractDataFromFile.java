package com.example.demo.utility.extracting;

import com.example.demo.entity.StorageLogs;
import com.example.demo.enums.LogLevel;
import com.example.demo.repository.StorageLogsRepository;
import com.example.demo.utility.factory.Factory;
import org.springframework.stereotype.Component;


import java.util.regex.Pattern;


@Component
public class ExtractDataFromFile implements IExtractDataFromFile {

    private String timeStamp = null;
    private String logType = null;
    private Integer ProcessId = null;
    private String message = null;
    private String threadName = null;

    private final Factory factory;
    private final StorageLogsRepository storageLogsRepository;

    public ExtractDataFromFile(Factory factory, StorageLogsRepository storageLogsRepository) {
        this.factory = factory;
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
        // estraggo il valore timestamp
        String timeStamp = line.substring(0, line.indexOf(" "));

        // controllo che il valore estratto sia campatibile con un timestamp
        String regexValidTimestamp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,3})?(Z|[+-]\\d{2}:\\d{2})?$";
        Pattern pattern = Pattern.compile(regexValidTimestamp);

        // e faccio il controllo
        // se compatibile setto la variabile altrimento ritorno un valore default
        if (pattern.matcher(timeStamp).matches()) {
            setTimeStamp(timeStamp);
        } else {
            setTimeStamp("1970-01-01T00:00:00Z");

        }

    }

    // estrarre il tipo di log dalla riga di log
    @Override
    public void extractLogType(String line) {

        if (LogLevel.checkIfStringContainsLogLevel(line)) {
            setLogType(
                    line.trim().substring(
                            line.indexOf(" "),
                            line.indexOf(LogLevel.returnWhichLevel(line)) + LogLevel.returnWhichLevel(line).length())
            );


        } else {
            setLogType("null");
        }

    }

    // estrarre il processId dalla linea di log
    // DA RIVEDERE
    @Override
    public void extractProcessId(String line) {

        try {

            String sottoStringa = line.substring(0, line.indexOf("---")).trim();
            int start = sottoStringa.lastIndexOf(" ");
            String pidString = sottoStringa.substring(start).trim();
            int pid = Integer.parseInt(pidString);
            setProcessId(pid);

        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            setProcessId(-1);
        }


    }


    // estrazione del nome del thread che ha eseguito l'operazione
    @Override
    public void extractThreadName(String line) {
        try {

            String subString = line.substring(line.indexOf("]") + 1);
            String threadName = subString.substring(0, subString.indexOf("]") + 1);

            setThreadName(threadName);


        } catch (IndexOutOfBoundsException ex) {
            setThreadName("null");
        }


    }

    @Override
    public void extractMessage(String line) {
        try {
            setMessage(
                    line.substring(
                            line.indexOf(" :")
                    )
            );
        } catch (IndexOutOfBoundsException ex) {
            setMessage("null");
        }
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
