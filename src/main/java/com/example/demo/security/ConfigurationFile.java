package com.example.demo.security;

import com.example.demo.repository.App_UserRepository;
import com.example.demo.utility.adapter.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class ConfigurationFile {

    private final GenerateToken generateToken;
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oauth2SuccessHandler;


    public ConfigurationFile(GenerateToken generateToken, JwtAuthFilter jwtAuthFilter, OAuth2SuccessHandler oauth2SuccessHandler) {
        this.generateToken = generateToken;
        this.jwtAuthFilter = jwtAuthFilter;
        this.oauth2SuccessHandler = oauth2SuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // nconfigurazione dei filtri spring per filtrare le richieste in entrata sul server
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        //  .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/oauth2/**",
                                "/login/oauth2/**",
                                "/sendMessage/toMe",
                                "/ws",
                                "/ws/**"
                        ).permitAll()
                        .requestMatchers("/auth/get/all").authenticated()
                        .requestMatchers("/auth/edit").authenticated()
                        .requestMatchers("/chat/rest/*").authenticated()
                        .requestMatchers("/sendMessage/replay").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // questa parte di filtro gestisce qualora venga chiamato oauth2 e venga inviata una richiesta di autorizzazione/auth tramite google
                // la classe che gestisce la risposta sarà quella specificata in ".successHandler(oauth2SuccessHandler)"
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth2/authorization"))
                        .successHandler(oauth2SuccessHandler)
                )

                .addFilterBefore(new JwtAuthFilter(generateToken),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public UserDetailsService userDetailsService(App_UserRepository repository) {
        return new CustomUserDetailsService(repository);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
