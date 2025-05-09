package com.example.demo.repository;

import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long> {

    @Query("select a from Anagrafica a ")
    Anagrafica getAnagraficaByAppUser(App_User appUser);
}
