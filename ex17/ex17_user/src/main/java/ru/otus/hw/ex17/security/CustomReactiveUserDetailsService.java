package ru.otus.hw.ex17.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17.repositories.UserRepository;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@AllArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByLogin(username)
                .map(user -> withUsername(user.getLogin())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build());
    }
}
