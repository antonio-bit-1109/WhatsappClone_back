package com.example.demo.repository;

import com.example.demo.dto.responses.ExtraMinimalUserInfo;
import com.example.demo.entity.App_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select u from App_User u" +
            " where  u.username = :username")
    Optional<App_User> getAdminUserByUsername(String username);

    Optional<App_User> getUserByEmail(String email);


    //    --prende gli utenti con cui
//--l'utente che sta facendo
//            --la richiesta ha gia una chat attiva nella cte
//-- dopo do che prendo tutti gli utenti eccetto quelli presenti nella cte
//-- in questo modo ottengo tutti gli utenti con cui
//-- utente che sta facendo la richiesta al server puo avviare una chat
    @Query(value = "WITH ALREADYINCHAT AS (" +
            "    SELECT DISTINCT " +
            "        U.ID ," +
            "        U.EMAIL ," +
            "        U.USERNAME ," +
            "        AN.NOME, " +
            "        AN.COGNOME " +  // Assicurati che ci sia uno spazio dopo AN.COGNOME
            "    FROM " +
            "        APP_USER_LISTA_CHATS APLC " +
            "        INNER JOIN CHAT C ON C.ID = APLC.LISTA_CHATS_ID " +
            "        INNER JOIN CHAT_PARTICIPANTS CP ON CP.CHAT_ID = C.ID " +
            "        INNER JOIN APP_USER U ON U.ID = CP.PARTICIPANTS_ID " +
            "        INNER JOIN ANAGRAFICA AN ON AN.ID = U.ANAGRAFICA_ID" +
            "    WHERE " +
            "        APLC.APP_USER_ID = :idUser " +
            "        AND U.ID != :idUser " +
            ") " +
            "SELECT " +
            "    AU.ID, " +
            "    AU.USERNAME, " +
            "    AN.NOME, " +
            "    AN.COGNOME, " +
            " AU.PROFILE_IMAGE" +  // Lo spazio dopo AN.COGNOME è fondamentale
            " FROM " +           // Assicurati che ci sia uno spazio prima di FROM
            "    APP_USER AU " +
            "    INNER JOIN ANAGRAFICA AN ON AN.ID = AU.ANAGRAFICA_ID " +
            " WHERE " +
            "    AU.ID != :idUser " +
            "    AND AU.ID NOT IN (SELECT ID FROM ALREADYINCHAT)",
            nativeQuery = true)
    List<ExtraMinimalUserInfo> getAllUsersICanHaveAChatWith(@Param("idUser") Long idUser);

}
