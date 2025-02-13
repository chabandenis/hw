package ru.otus.hw.ex12hw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/user/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin( Customizer.withDefaults())
/*                .formLogin(formLogin -> formLogin
                        //.loginPage("/login") // URL страницы логина
                        .defaultSuccessUrl("http://localhost:5173/") // URL на которую перенаправить после успешного логина
                        //.permitAll()
                )*/
                .rememberMe(rm -> rm.key("AnyKey")
                        .tokenValiditySeconds(600))
        //.httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
        return NoOpPasswordEncoder.getInstance();

    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .builder()
                .username("user1")
                .password("1")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
