package com.reggie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class reggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(reggieApplication.class,args);
        log.info("Successful");
    }
}
