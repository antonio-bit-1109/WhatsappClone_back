package com.example.demo.repository;

import com.example.demo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat getChatById(Long id);
}
