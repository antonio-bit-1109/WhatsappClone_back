package com.example.demo.utility.extracting;

public interface IExtractDataFromFile {
    void extractTimeStamp(String line);

    void extractLogType(String line);

    void extractProcessId(String line);

    void extractMessage(String line);

    void extractThreadName(String line);

    void BuildRecordForDb();


}
