package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.Role;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.enums.RoleEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.repository.RoleRepository;
import com.mai.projects.plm.repository.UserRepository;
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
	public void register(User user) {
		Role roleUser = roleRepository.findByName(RoleEnum.USER.getName()).orElseThrow(() -> new ServerException(ErrorEnum.ROLE_NOT_FOUND, List.of(user.getUserName())));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(List.of(roleUser));
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		if (CollectionUtils.isEmpty(userList)) {
			throw new ServerException(ErrorEnum.USERS_NOT_FOUND, Collections.emptyList());
		}
		return userList;
	}

	@Override
	public User findByUserName(String username) {
		return userRepository
				.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ServerException(ErrorEnum.USERS_NOT_FOUND));
	}


}
