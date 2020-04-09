package com.mai.projects.plm.security.jwt;

import com.mai.projects.plm.security.jwt.model.JwtUser;
import com.mai.projects.plm.entities.Role;
import com.mai.projects.plm.entities.Status;
import com.mai.projects.plm.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public static JwtUser create(User user) {
        return JwtUser.builder()
                .email(user.getEmail())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUserName())
                .firstName(user.getFirstName())
                .authorities(mapToGrantedAuthorities(user.getRoles()))
                .enabled(user.getStatus().equals(Status.ACTIVE))
                .lastPasswordResetDate(user.getUpdated()).build();

    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
