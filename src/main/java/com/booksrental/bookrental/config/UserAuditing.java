package com.booksrental.bookrental.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String name = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return Optional.of(name);
    }
}
