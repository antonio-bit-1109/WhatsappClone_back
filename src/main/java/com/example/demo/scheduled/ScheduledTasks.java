package com.example.demo.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTasks {


    @Scheduled(fixedRate = 3600000)
    public void StoreAndDeleteLogs() {
    }
}
