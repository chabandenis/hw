package ru.otus.hw.ex13.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
public class MethodSecurityConfiguration {
}

