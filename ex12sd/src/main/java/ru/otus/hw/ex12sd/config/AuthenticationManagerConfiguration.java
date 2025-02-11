package ru.otus.hw.ex12sd.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import ru.otus.hw.ex12sd.secutiry.jwt.RefreshTokenAuthenticationProvider;
import ru.otus.hw.ex12sd.secutiry.jwt.TokenAuthenticationProvider;
import ru.otus.hw.ex12sd.secutiry.login.LoginAuthenticationProvider;

@Configuration
public class AuthenticationManagerConfiguration {

    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    private final LoginAuthenticationProvider loginAuthenticationProvider;

    private final RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider;

    public AuthenticationManagerConfiguration(final TokenAuthenticationProvider tokenAuthenticationProvider,
                                              final LoginAuthenticationProvider loginAuthenticationProvider,
                                              final RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider) {
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.loginAuthenticationProvider = loginAuthenticationProvider;
        this.refreshTokenAuthenticationProvider = refreshTokenAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(final ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        var auth = new AuthenticationManagerBuilder(objectPostProcessor);
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(tokenAuthenticationProvider);
        auth.authenticationProvider(refreshTokenAuthenticationProvider);
        return auth.build();
    }
}
