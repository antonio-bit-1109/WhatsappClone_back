package com.example.demo.scheduled;

import com.example.demo.utility.extracting.ExtractDataFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;

/**
 * This class manages scheduled tasks related to log processing and database storage.
 * It reads a log file, extracts log data, stores the extracted data in a database,
 * and deletes the log file at a scheduled interval.
 * <p>
 * The class uses the {@link ExtractDataFromFile} component to extract individual log details
 * such as timestamp, log type, process ID, thread name, and message from each log line.
 * <p>
 * Features:
 * - Scheduled task to process and store log data every 6 hours.
 * - Log file deletion after successful data storage to manage storage.
 * - Error handling for file reading and deletion issues.
 * <p>
 * Dependencies:
 * - {@code ExtractDataFromFile} for log data extraction.
 * - Logger for logging process information and errors.
 */
@Component
public class ScheduledTasks {

    private final ExtractDataFromFile extractDataFromFile;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);


    public ScheduledTasks(ExtractDataFromFile extractDataFromFile) {
        this.extractDataFromFile = extractDataFromFile;
    }


    // salvataggio su db e cancellazione dei log ogni 6 ore
    @Scheduled(fixedRate = 21600000)
//    @Scheduled(fixedRate = 60000)
    public void StoreAndDeleteLogs() {

        try (BufferedReader reader = new BufferedReader(new FileReader("logging/application.log"))) {
            String line = reader.readLine();

            while (line != null) {

                if (line.trim().isEmpty()) continue;

                this.extractDataFromFile.extractTimeStamp(line);
                this.extractDataFromFile.extractLogType(line);
                this.extractDataFromFile.extractMessage(line);
                this.extractDataFromFile.extractProcessId(line);
                this.extractDataFromFile.extractThreadName(line);
                this.extractDataFromFile.BuildRecordForDb();

                // read next line
                line = reader.readLine();
            }
            logger.atInfo().log("file di log salvato sul db. --" + LocalDateTime.now());
            if (this.deletedFileLog()) {
                logger.atInfo().log("cancellazione log effettuata con successo" + LocalDateTime.now());
            }


        } catch (IOException e) {
            logger.atError().log("errore durante la lettura del file di log per il salvataggio sul db" + e.getMessage());
        }
    }


    /**
     * Deletes the log file located at "logging/application.log".
     * If the deletion is successful, the method returns true.
     * In case of a security exception or failure to delete the file, it logs an error message
     * and returns false.
     *
     * @return true if the file is successfully deleted, otherwise false
     */
    private boolean deletedFileLog() {
        try {
            File logFile = new File("logging/application.log");
            return logFile.delete();
        } catch (SecurityException ex) {
            logger.atError().log("errore durante la cancellazione del file di log, post salvataggio su db.");
        }
        return false;
    }
}
