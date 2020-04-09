package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.repository.RoleRepository;
import com.mai.projects.plm.repository.UserRepository;
import com.mai.projects.plm.entities.Role;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        Role roleUser= roleRepository.findByName(RoleEnum.USER.getName()).orElseThrow(()->new ServerException(ErrorEnum.ROLE_NOT_FOUND, List.of(user.getUserName())));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleUser));

        User registeredUser= userRepository.save(user);

        log.info("IN register- user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userRepository.findAll();
        if(CollectionUtils.isEmpty(userList)){
            throw new ServerException(ErrorEnum.USERS_NOT_FOUND, Collections.emptyList());
        }
        log.info("Size: {} ", userList.size());
        return userList;
    }

    @Override
    public User findByUserName(String username) {
        User user = userRepository
                .findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User with username "+ username+" not found"));
        log.info("user {} was successfuly find", username);
        return user;
    }

    @Override
    public User findById(Long id) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
