package com.mai.projects.plm.repository;

import com.mai.projects.plm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String name);

}
