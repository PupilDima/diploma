package com.kovalenko.diploma.entityDetails;

import com.kovalenko.diploma.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {
    private final List<GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String login;

    public UserSecurityDetails(User user) {
        this.password = user.getPassword();
        this.login = user.getLogin();
        this.grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(user.getUserType().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
