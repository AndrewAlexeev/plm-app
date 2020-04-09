package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByUserName(String username);
    User findById(Long id);
    void delete(Long id);
}
