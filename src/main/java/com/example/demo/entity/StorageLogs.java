package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class StorageLogs {


    public StorageLogs(String threadName, String message, Integer processId, String logType, ZonedDateTime timestamp) {
        this.threadName = threadName;
        this.message = message;
        this.processId = processId;
        this.logType = logType;
        this.timestamp = timestamp;
    }

    public StorageLogs() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime timestamp;  // 2025-05-10T11:29:45.763+02:00

    @Column(nullable = false)
    private String logType;

    @Column(nullable = false)
    private Integer processId;

    @Lob
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String threadName;

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
