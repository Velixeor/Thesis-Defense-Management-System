package ru.tubryansk.tdms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LifeCycleService {
    @EventListener(ContextStartedEvent.class)
    public void onStartup(ContextStartedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        log.info("Static files location: {}", applicationContext.getEnvironment().getProperty("spring.web.resources.static-locations"));
    }
}