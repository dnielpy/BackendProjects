package com.example.demo.repositories;

import com.example.demo.entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String name);
}
