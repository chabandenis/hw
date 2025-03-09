package ru.otus.hw.ex15;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex15.models.User;
import ru.otus.hw.ex15.repositories.UserRepository;

@AllArgsConstructor
@Component
public class Run implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; ++i) {
            userRepository.save(new User("Ivan"));
        }
    }
}
