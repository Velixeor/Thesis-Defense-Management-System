package ru.tubryansk.tdms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@Slf4j
public class TdmsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TdmsApplication.class, args);
        String staticLocation = context.getEnvironment().getProperty("spring.web.resources.static-locations");
        log.info("Static location: {}", staticLocation);
    }
}
