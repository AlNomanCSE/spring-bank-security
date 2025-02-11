package com.noman.security.events;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {
    @EventListener
    public void onSuccess(AuthorizationDeniedEvent event) {
        log.info("Authorization Failed for the User : {} due to {} ", event.getAuthentication().get().getName(), event.getAuthentication().get().toString());
    }

}
