package ru.otus.hw.ex12_r_hw.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
public class MethodSecurityConfiguration {
}

