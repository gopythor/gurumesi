package com.zerobase.gurumesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class GurumesiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GurumesiApplication.class, args);
    }

}
