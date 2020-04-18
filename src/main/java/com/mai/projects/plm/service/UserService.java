package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.User;

import java.util.List;

public interface UserService {

    void register(User user);
    List<User> findAll();
    User findByUserName(String username);
    User findById(Long id);
}
