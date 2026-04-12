package edu.eci.dosw.TechCup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
// EnableWebSecurity es una notación de spring security que permite la configuración de seguridad web

public class SecurityConfig {
    // Password encoder es una interfaz de spring security que se usa para cifrar contraseñas y verificarlas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder es una clase que implementa PasswordEncoder. Usa el algoritmo BCrypt para el cifrado.
        return new BCryptPasswordEncoder();
    }
    // AuthenticationManager es una interfaz de spring security que se encarga de procesar las autenticaciones de los usuarios. Recibe las credenciales y las valida.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }


}
