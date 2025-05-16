package com.example.demo.service;

import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.enums.AuthProvider;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import com.example.demo.utility.exception.EmailAlreadyPresentInDb;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class OAuth2Service {


    private final App_UserRepository userRepository;
    private final AnagraficaRepository anagraficaRepository;

    // classe utilizzata per chiamare la google people api per ricavare dati aggiuntivi dagli utenti autenticati tramite google
//    private final OAuth2AuthorizedClientService authorizedClientService;
//    private final RestClient.Builder builder;


    // classe incaricata di estrarre lo user oauth inviato da google ed estrarre i dati necessari a creare un utente autenticato con google sul mio db
    public OAuth2Service(App_UserRepository userRepository, AnagraficaRepository anagraficaRepository

//                        OAuth2AuthorizedClientService         authorizedClientService, RestClient.Builder builder
    ) {
        this.userRepository = userRepository;
        this.anagraficaRepository = anagraficaRepository;
//        this.authorizedClientService = authorizedClientService;
//        this.builder = builder;
    }

    public App_User processOAuth2User(OAuth2User oauth2User) {

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("given_name");
        String surname = oauth2User.getAttribute("family_name");
        String sub = oauth2User.getAttribute("sub"); // ID Google univoco
        String picture = oauth2User.getAttribute("picture");


        // recupera l'authentication token di google per fare la chiamata alla google people api
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
//                oauthToken.getAuthorizedClientRegistrationId(),
//                oauthToken.getName());
//        String GoogleAccess_Token_peopleAPI = client.getAccessToken().getTokenValue();


        //PER OTTENERE DATI SENSIBILI QUALI BIRTHDAY E TELEFONO DEVO VERIFICARE L'APP SUL GOOGLE CLOUD PLATFORM
        // utilizzando webclient faccio una chiamata alla peopleAPI di google per ricavare i dati che mi servono
//        try {
//            WebClient webClient = WebClient.builder().build();
//
//            Map map = webClient.get()
//                    .uri("https://people.googleapis.com/v1/people/me?personFields=birthdays,phoneNumbers")
//                    .header("Authorization", "Bearer " + GoogleAccess_Token_peopleAPI)
//                    .retrieve()
//                    .bodyToMono(Map.class)
//                    .block();
//
//
//            System.out.println(map);
//        } catch (RuntimeException e) {
//
//            throw new GoogleErrorAuthentication("errore durante l'autenticazione presso google people API:" + e.getMessage());
//        }


        Optional<App_User> existingUser = userRepository.getUserByEmail(email);

        if (existingUser.isPresent()) {
            if (existingUser.get().getProvider() == AuthProvider.GOOGLE) {
                return existingUser.get();
            } else {
                throw new EmailAlreadyPresentInDb("Account registrato gia sull app con questa email: " + email +
                        " Effettua accesso direttamente in app o usa una mail differente.");
            }
        }

        // Crea nuovo utente
        App_User newUser = new App_User();
        newUser.setUsername(email);
        newUser.setProfileImage(picture);
        newUser.setEmail(email);
        newUser.setProvider(AuthProvider.GOOGLE);
        newUser.setPassword("null");
        newUser.setProviderId(sub);
        newUser.setRole("USER");
        newUser.setIsEnabled(true);
        newUser.setAccountNotExpired(true);
        newUser.setAccountNotLocked(true);
        newUser.setCredentialsNonExpired(true);

        App_User savedUser = userRepository.save(newUser);

        // Crea anagrafica
        Anagrafica anagrafica = new Anagrafica();
        anagrafica.setNome(name);
        anagrafica.setCognome(surname);
        anagrafica.setUser(savedUser);
        anagrafica.setCf(null);
        anagrafica.setDataNascita(null);
        anagrafica.setLuogoDiNascita(null);
        anagrafica.setTelefono(null);
        Anagrafica savedAnagrafica = anagraficaRepository.save(anagrafica);

        savedUser.setAnagrafica(savedAnagrafica);
        return userRepository.save(savedUser);

    }

}
