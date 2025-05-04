package com.example.demo.repository;

import com.example.demo.entity.App_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface App_UserRepository extends JpaRepository<App_User, Long> {
}
