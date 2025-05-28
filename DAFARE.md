-[x] implementa la generazione del token al login --> studia auth2.0 senza jwt
 -[] tabella chat e tabella gruppi
 -[] tabella media associati ad utente (foto video musica ecc ecc)
 -[] status messaggio (letto, non letto)
-[x] controlla se la get funziona
-[x] controlla come funziona sonar
-[x] implementazioni logger per documentare gli avvenimenti
-[x] implementa un modo per salvare i logs in una tabella a parte e ripulire il file (ogni ora)
 (enum per il tipo di log)??

-[] sistema il crea nuova chat

-[] una volta che l utente accede al componente /home/chats-main si connette ai socket specifici di ogni chat
/chat-private/chatidentity e voglio fare che ogni volta che un utente invia un nuovo messaggio questo viene salvato su
db
e inviato su socket specifico della chat e se il socket registra un nuovo messaggio aggiunto notifica il front end per
ricaricare
l'oggetto contenente i messaggi per mostrarli aall utente