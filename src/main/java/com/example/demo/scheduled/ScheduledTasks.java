package com.example.demo.scheduled;

import com.example.demo.utility.extracting.ExtractDataFromFile;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ScheduledTasks {

    BufferedReader reader;
    private final ExtractDataFromFile extractDataFromFile;

    public ScheduledTasks(ExtractDataFromFile extractDataFromFile) {
        this.extractDataFromFile = extractDataFromFile;
    }


    @Scheduled(fixedRate = 60000)
    public void StoreAndDeleteLogs() {

        try {
            reader = new BufferedReader(new FileReader("logging/application.log"));
            String line = reader.readLine();

            while (line != null) {

                if (line.trim().isEmpty()) continue;

                this.extractDataFromFile.extractTimeStamp(line);
                this.extractDataFromFile.extractMessage(line);
                this.extractDataFromFile.extractLogType(line);
                this.extractDataFromFile.extractProcessId(line);
                this.extractDataFromFile.extractThreadName(line);
                this.extractDataFromFile.BuildRecordForDb();
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
