package com.example.demo.repository;

import com.example.demo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat getChatById(Long id);

    Optional<Chat> getChatByIdentity(UUID identity);
}
