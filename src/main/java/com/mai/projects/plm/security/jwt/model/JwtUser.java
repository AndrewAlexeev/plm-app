package com.mai.projects.plm.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Builder
@Data
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
