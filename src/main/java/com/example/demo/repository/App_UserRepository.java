package com.example.demo.repository;

import com.example.demo.entity.App_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface App_UserRepository extends JpaRepository<App_User, Long> {

    @Query("select u from App_User u" +
            " where  u.username = :username")
    Optional<App_User> getUserByIsUsername(String username);

    @Query(" select u from App_User u where u.id = :id")
    Optional<App_User> getApp_UserById(Long id);
}
