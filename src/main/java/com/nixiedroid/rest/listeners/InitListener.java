package com.nixiedroid.rest.listeners;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Holder for One-time run code for initial setup
 */
@Component
public class InitListener {

    @EventListener(ApplicationReadyEvent.class)
    public void initRoutine() {

    }

}
