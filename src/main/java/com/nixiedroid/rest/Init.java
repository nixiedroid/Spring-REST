package com.nixiedroid.rest;

import com.nixiedroid.rest.models.Coffee;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Component
public class Init {

        @EventListener(ApplicationReadyEvent.class)
        public void initRoutine() {
                System.out.println("Init routine");
                org.springframework.http.HttpStatus.valueOf(404).name();
        }

        public Init() {
                var c = new Coffee("CAppucino");
                var a = new Coffee("Americano");
        }

}
