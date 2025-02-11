package ru.otus.hw.ex12sd.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.hw.ex12sd.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String username;
//    private final String email;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(final User user, final Collection<? extends GrantedAuthority> authorities) {
        this.id = user.getId();
        this.username = user.getLogin();
        //this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user, buildGrantedAuthorities(user));
    }

    private static List<GrantedAuthority> buildGrantedAuthorities(final User user) {
        return null;
/*
        return user.getRoles().stream()
                .flatMap(role -> role.getPrivileges().stream())
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                .collect(Collectors.toList());
*/
    }

    public Long getId() {
        return id;
    }
/*
    public String getEmail() {
        return email;
    }
*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
