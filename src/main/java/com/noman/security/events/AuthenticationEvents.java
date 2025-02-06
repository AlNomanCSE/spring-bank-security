package com.noman.security.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        log.info("AuthenticationSuccessEvent : {}", event.getAuthentication());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        log.error("Login failed for : {} due to {}", event.getAuthentication().getName(), event.getException().getMessage());
    }
}
