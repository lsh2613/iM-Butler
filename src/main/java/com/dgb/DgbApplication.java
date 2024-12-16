package com.dgb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DgbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgbApplication.class, args);
    }

}
