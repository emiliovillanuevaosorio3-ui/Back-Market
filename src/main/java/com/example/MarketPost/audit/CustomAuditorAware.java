package com.example.MarketPost.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

    private final static String USER_MOCK = "Admin";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USER_MOCK);
    }
}
