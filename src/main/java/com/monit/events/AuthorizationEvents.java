package com.monit.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {

    @EventListener
    public void onAccessDenied(AuthorizationDeniedEvent deniedEvents) {
        log.error("Authorization failed for the user: {} due to:{}", deniedEvents.getAuthentication().get().getName()
                , deniedEvents.getAuthorizationDecision().toString());
    }
}
