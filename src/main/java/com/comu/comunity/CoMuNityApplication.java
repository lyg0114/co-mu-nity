package com.comu.comunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoMuNityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoMuNityApplication.class, args);
    }

}
