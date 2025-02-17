package ru.otus.hw.ex12hw.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true
)
public class MethodSecurityConfiguration {
}
