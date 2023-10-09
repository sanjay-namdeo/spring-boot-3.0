package com.spring.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.boot.entity.UserAccount;
import com.spring.boot.repository.UserManagementRepository;
import com.spring.boot.repository.UserRepository;

@Configuration
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return username -> repository.findByUsername(username).asUser();
    }

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("user", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
        };
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/get-videos", "/search-by-name", "/search-by-id")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/create-video")
                .hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
