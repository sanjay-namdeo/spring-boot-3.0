package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.UserAccount;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
    
}
