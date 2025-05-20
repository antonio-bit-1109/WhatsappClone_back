package com.example.demo.repository;

import com.example.demo.entity.StoreMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMessageRepository extends JpaRepository<StoreMessages, Long> {
}
