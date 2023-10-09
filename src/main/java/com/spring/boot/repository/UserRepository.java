package com.spring.boot.repository;

import org.springframework.data.repository.Repository;

import com.spring.boot.entity.UserAccount;

public interface UserRepository extends Repository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
