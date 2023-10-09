package com.spring.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
