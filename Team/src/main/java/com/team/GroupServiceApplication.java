package com.team;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class GroupServiceApplication {
    private static final Logger LOGGER = LogManager.getLogger(GroupServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Booting application");
        SpringApplication.run(GroupServiceApplication.class, args);
    }

}
