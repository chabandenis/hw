package ru.otus.hw.ex12sd.secutiry.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex12sd.dto.user.UserLoginDto;
import ru.otus.hw.ex12sd.mapper.UserMapper;
import ru.otus.hw.ex12sd.services.UserDetailsImpl;
import ru.otus.hw.ex12sd.services.UserService;

@Component
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public RefreshTokenAuthenticationProvider(final UserService userService,
                                              final JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        String username = tokenProvider.getUserNameFromJwtToken(token);
        UserDetailsImpl userDetails = getUserDetails(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private UserDetailsImpl getUserDetails(String username) {
        return UserDetailsImpl.build(UserMapper.toUser(userService.findByLogin(new UserLoginDto(username, null))));

    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return (RefreshJwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
