package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.boot.logging.LogLevel;

import java.time.ZonedDateTime;

@Entity
public class StorageLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime timestamp;  // 2025-05-10T11:29:45.763+02:00

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private LogLevel level;  // DEBUG, INFO, WARN, ERROR, TRACE, FATAL

    @Column(nullable = false)
    private Integer processId;  // 604

    @Column(length = 50)
    private String applicationName;  // whatappClone

    @Column(length = 50)
    private String threadName;  // http-nio-8080-exec-6

    @Column(length = 255)
    private String logger;  // s.w.s.m.m.a.RequestMappingHandlerMapping

    @Column(length = 1000)
    private String message;  // Mapped to com.example.demo.controller.AuthController#register(UserRegistrationDTO)

    @Column(length = 255)
    private String className;  // com.example.demo.controller.AuthController

    @Column(length = 100)
    private String methodName;  // register

    @Column(length = 255)
    private String request;  // UserRegistrationDTO

    @Column(length = 4000)
    private String stackTrace;  // Per i log di tipo ERROR

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

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
