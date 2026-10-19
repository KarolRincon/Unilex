package com.unilex.app.Unilex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Páginas públicas que cualquiera puede ver sin iniciar sesión:
                        .requestMatchers(
                                "/",
                                "/quienes-somos",
                                "/servicios",
                                "/planes",
                                "/asistente-legal-ia",
                                "/auth/**",
                                "/images/**",
                                "/css/**",
                                "/js/**"
                        ).permitAll()
                        // Todas las demás rutas (como el Dashboard o /procesos) requerirán iniciar sesión
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login") // Tu página personalizada de login
                        .defaultSuccessUrl("/", true) // A dónde redirigir al iniciar sesión con éxito
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}