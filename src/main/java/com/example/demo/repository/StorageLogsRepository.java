package com.example.demo.repository;

import com.example.demo.entity.StorageLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageLogsRepository extends JpaRepository<StorageLogs, Long> {
}
