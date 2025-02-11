package ru.otus.hw.ex12sd.secutiry.rest;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex12sd.secutiry.jwt.JwtPair;
import ru.otus.hw.ex12sd.secutiry.jwt.JwtTokenProvider;
import ru.otus.hw.ex12sd.utils.JsonUtils;

import java.io.IOException;


@Component(value = "loginAuthenticationSuccessHandler")
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;

    public LoginAuthenticationSuccessHandler(final JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtPair jwtPair = tokenProvider.generateTokenPair(userDetails);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JsonUtils.writeValue(response.getWriter(), jwtPair);

    }
}
