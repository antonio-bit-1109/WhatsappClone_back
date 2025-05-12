package com.example.demo.scheduled;

import com.example.demo.service.AuthService;
import com.example.demo.utility.extracting.ExtractDataFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ScheduledTasks {

    private final ExtractDataFromFile extractDataFromFile;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);


    public ScheduledTasks(ExtractDataFromFile extractDataFromFile) {
        this.extractDataFromFile = extractDataFromFile;
    }


    @Scheduled(fixedRate = 60000)
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

        } catch (IOException e) {
            logger.atError().log("errore durante la lettura del file di log per il salvataggio sul db" + e.getMessage());
        }
    }


}
