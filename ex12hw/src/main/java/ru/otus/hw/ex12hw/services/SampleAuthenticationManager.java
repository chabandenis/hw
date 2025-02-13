package ru.otus.hw.ex12hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Component
public class SampleAuthenticationManager implements AuthenticationManager {


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        Collection<GrantedAuthority> roles = Collections.emptyList();
//        if (auth.getName().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), roles);
//        }
//        throw new BadCredentialsException("Bad credentials");
    }
}
